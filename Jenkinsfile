
pipeline {
    agent any

    stages {
        stage('Checkout File Upload API') {
            steps {
                dir('MainBackend') {
                    git branch: 'main',
                        url: 'https://github.com/Raviprajapati808562/Car_Fileupload_api.git'
                }
            }
        }

        stage('Checkout Car API') {
            steps {
                dir('CarApi') {
                    git branch: 'master',
                        url: 'https://github.com/Raviprajapati808562/Car_Fileupload_api.git'
                }
            }
        }

        stage('Verify Maven') {
            steps {
                bat '"C:\\apache-maven-3.9.16\\bin\\mvn.cmd" -version'
            }
        }

        stage('Build File Upload API') {
            steps {
                dir('MainBackend') {
                    bat 'call "C:\\apache-maven-3.9.16\\bin\\mvn.cmd" clean package -DskipTests'
                }
            }
        }

        stage('Build Car API') {
            steps {
                dir('CarApi') {
                    bat 'call "C:\\apache-maven-3.9.16\\bin\\mvn.cmd" clean package -DskipTests'
                }
            }
        }

        stage('Prepare Docker Context') {
            steps {
                bat '''
                    if not exist docker-context mkdir docker-context

                    copy /Y MainBackend\\target\\BACKEND-API.jar docker-context\\main.jar
                    if errorlevel 1 exit /b 1

                    copy /Y CarApi\\target\\CarApi-0.0.1-SNAPSHOT.jar docker-context\\car.jar
                    if errorlevel 1 exit /b 1
                '''
            }
        }

        stage('Create Docker Files') {
            steps {
                dir('docker-context') {
                    writeFile file: 'Dockerfile', text: '''FROM eclipse-temurin:17-jre
WORKDIR /app
COPY main.jar /app/main.jar
COPY car.jar /app/car.jar
COPY start.sh /app/start.sh
EXPOSE 9091 8081
ENTRYPOINT ["/bin/sh", "/app/start.sh"]
'''

                    writeFile file: 'start.sh', text: '''#!/bin/sh
java -jar /app/car.jar &
CAR_PID=$!

java -jar /app/main.jar &
MAIN_PID=$!

wait "$MAIN_PID"
'''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t banking-app:latest docker-context'
            }
        }


stage('Run Docker Container') {
    steps {
        bat '''
            docker rm -f banking-app 2>NUL

            docker run -d --name banking-app ^
                -p 9092:9091 ^
                -p 8082:8081 ^
                banking-app:latest

            if errorlevel 1 exit /b 1
        '''
    }
}


        stage('Verify Deployment') {
            steps {
                bat 'docker ps --filter name=banking-app'
                bat 'docker logs banking-app'
            }
        }
    }

    post {
        success {
            echo 'Both APIs built and the Docker container was started.'
        }

        failure {
            echo 'Pipeline failed. Check the Jenkins Console Output.'
        }
    }
}
