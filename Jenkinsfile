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

        stage('Build File Upload API') {
            steps {
                dir('MainBackend') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Car API') {
            steps {
                dir('CarApi') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Prepare Docker Context') {
            steps {
                bat '''
                    if not exist docker-context mkdir docker-context

                    copy /Y MainBackend\\target\\BACKEND-API.jar docker-context\\main.jar
                    if errorlevel 1 exit /b 1

                    copy /Y CarApi\\target\\*.jar docker-context\\car.jar
                    if errorlevel 1 exit /b 1
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t banking-app:latest docker-context'
            }
        }

        stage('Run Container') {
            steps {
                bat '''
                    docker rm -f banking-app 2>NUL
                    docker run -d --name banking-app -p 9091:9091 -p 8081:8081 banking-app:latest
                    if errorlevel 1 exit /b 1
                '''
            }
        }
    }

    post {
        success {
            echo 'Both API branches built and the container was started.'
        }
        failure {
            echo 'Pipeline failed. Check the Jenkins console output.'
        }
    }
}
