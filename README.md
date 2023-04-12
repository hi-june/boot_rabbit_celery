# boot_rabbit_celery
sending message between different language  
서로 다른 언어간의 메시지 송수신

## 개요
서버를 spring boot를 사용할 때,  
특정 작업(ex. AI 모델 관련 작업)에서 python 코드를 사용해야할 경우, 해당 작업을 celery worker가 수행할 수 있도록 만들었습니다.  
이 프로젝트는 spring boot에서 메시지를 rabbitMQ에 publish하고, queue에 쌓인 message를 celery가 consume하는 간단한 예시 프로젝트입니다.  
이 프로젝트를 참고하여 필요한 부분은 직접 추가 후 사용하시면 될 것 같습니다 :)

## 구성
publisher: spring boot(java)  
broker: rabbitMQ  
consumer: celery worker(python)  

## 실행방법(로컬)
- 환경 세팅
1. clone을 받습니다.
2. 'message_publisher' 폴더를 프로젝트 루트로 intelliJ에서 open 합니다.
3. 'message_consumer' 폴더를 프로젝트 루트로 vscode에서 open 합니다.
4. 각각 rabbitMQ와 celery 관련 설정을 해줍니다.(port, host, url 등등)
<br>

- 순서
1. rabbitMQ 실행  
```
rabbitmq-server
```

2. celery 실행  
```
celery -A celery_consume worker loglevel=info
```

3. spring boot 실행 후, "{host}:{port}/send"에 post request를 보냅니다.
```
{
    "type": "ETL",
    "title": "Test Message",
    "message": "hi june!"
}
```
