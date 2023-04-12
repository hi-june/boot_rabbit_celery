import json

from celery import Celery
from celery import bootsteps
from kombu import Consumer, Exchange, Queue

queue = Queue("input.queue", Exchange("default"), "input.key")

app = Celery()
app.config_from_object("celery_config")


# Decalring the general input message handler
class InputMessageHandler(object):
    def handle(self, body):
        _type = body["type"]
        
        # if you want to accept only specific type of message, you need below
        if _type == "ETL":
            ETLMessageHandler().handle(body)
        
        # if body is json type, you need below
        '''
        body_json = json.loads(body)  
        _type = body_json["type"]

        if _type == "ETL":
            ETLMessageHandler().handle(body_json)
        '''


# Declaring the ETL message handler
class ETLMessageHandler(object):
    def handle(self, body):
        print(f"Working on ETL for message: {body}")
        # Calling out your Celery tasks here
        _title = body["title"]
        _message = body["message"]
        print(f"title: {_title}, message: {_message}")


# Declaring the bootstep for our purposes
class InputMessageConsumerStep(bootsteps.ConsumerStep):
    def get_consumers(self, channel):
        return [Consumer(channel,
                         queues=[queue],
                         callbacks=[self.handle_message],
                         accept=["json"])]

    def handle_message(self, body, message):
        InputMessageHandler().handle(body)
        message.ack()


app.steps["consumer"].add(InputMessageConsumerStep)