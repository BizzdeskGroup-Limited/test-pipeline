node {
    stage 'Check out'
    echo 'Checking out...'
    checkout scm
    stage 'Test'
    echo 'Testing...'
    sh 'mvn clean test'
    stage 'Build Jar'
    echo 'Building Jar file...'
    sh 'mvn clean package'
    stage 'Build  Docker Image'
    docker.withRegistry("${env.REGISTRY_PROTOCOL}://${env.REGISTRY_HOST}", 'docker_registry_credentials_id') {
        stage 'Build Docker Image'
        echo 'Building docker image....'
        String imageName = "${env.REGISTRY_HOST}/test-pipeline:latest"
        sh "docker build -t ${imageName} ."
        def img = docker.image(imageName)
        stage 'Push Docker Image'
        echo 'Pushing docker image....'
        img.push()
    }
    stage 'Deploy to Kubernetes'
    echo 'Deploying....'
    sh "helm upgrade --install test-pipeline ./helm --set image.repository=${env.REGISTRY_HOST}/test-pipeline --set replicaCount=${env.REPLICAS}"
}