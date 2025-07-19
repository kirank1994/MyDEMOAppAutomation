pipeline {
    agent any

    parameters {
        string(name: 'platform', defaultValue: 'IOS', description: 'Platform to run tests on (IOS or ANDROID)')
        string(name: 'group', defaultValue: 'regression', description: 'Test group to run (e.g., regression, E2E)')
    }

    stages {
        stage('Run Tests') {
            steps {
                script {
                    def suiteFile = ""
                    if (params.platform == "IOS") {
                        suiteFile = "testNGSuites/testng_IOS.xml"
                    } else if (params.platform == "ANDROID") {
                        suiteFile = "testNGSuites/testng_ANDROID.xml"
                    } else {
                        error "Invalid platform: ${params.platform}"
                    }

                    sh """
                        mvn clean test \
                        -DsuiteXmlFile=${suiteFile} \
                        -Dgroups=${params.group}
                    """
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/index.html', allowEmptyArchive: true
        }
    }
}