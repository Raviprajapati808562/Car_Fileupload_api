
pipeline {
    agent any

    stages {
        stage('Checkout Main Backend') {
            steps {
                dir('Learning-Boot') {
                    git branch: 'main',
                        url: 'https://github.com/Raviprajapati808562/backend_fileupload.git'
                }
            }
        }

        stage('Checkout Car API') {
            steps {
                dir('CarApi') {
                    git branch: 'master',
                        url: 'https://github.com/Raviprajapati808562/backend_fileupload.git'
                }
            }
        }

        stage('Build Main Backend') {
            steps {
                dir('Learning-Boot') {
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
    }
}
