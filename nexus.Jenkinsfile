pipeline {

    agent {
        node("test-executor")
    }

    parameters {
        string(name: 'branch', defaultValue: 'master', description: 'Branch to checkout')
        string(name: 'TAGS', defaultValue: '@Smoke',
            description: '''Cucumber tags to be executed. Examples:
                            @Regression,
                            @Sanity.
                            Leave this field empty if you want to run single feature class.
                            ''')
        string(name: 'FEATURE_CLASS', defaultValue: "src/test/resources/features/demoApk.feature",
            description: '''Select feature class to execute. Examples:
                            src/test/resources/features/views.feature,
                            src/test/resources/features/activities.feature.
                            Leave this field empty if you want to run tags.
                            ''')
        string(name: 'forks', defaultValue: '1', description: 'Number of parallel threads')
        string(name: 'device', defaultValue: 'nexus', description: 'Tests run on nexus')
    }

    stages {

        stage('Execute tests'){
            steps {
                script {
                    if ( !TAGS.isEmpty() ) {
                        bat "mvn clean test -Dcucumber.filter.tags=${TAGS} -Ddevice=${params.device} -Dforks=${params.forks}"
                    }
                    if ( !FEATURE_CLASS.isEmpty() ) {
                        bat "mvn clean test -Dcucumber.options=${FEATURE_CLASS} -Ddevice=${params.device} -Dforks=${params.forks}"
                    }
                }
            }
        }
    }

}