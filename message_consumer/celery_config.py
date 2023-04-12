# celeryconfig.py

# 'amqp://{user}:{password}@{host}:{port}/{virtual_host}'
broker_url = '' # set your broker url
task_serializer = 'json'
result_serializer = 'json'
accept_content = ['json']
timezone = 'Asia/Seoul'
enable_utc = True