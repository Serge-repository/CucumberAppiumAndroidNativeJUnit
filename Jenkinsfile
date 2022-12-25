pipeline {

    agent {
        node("test-executor")
    }

    parameters {
        string(name: 'branch', defaultValue: 'master', description: 'Branch to checkout')
        string(name: 'TAGS', defaultValue: '@Android',
            description: '''Cucumber tags to be executed. Examples:
                            @Android,
                            @Smoke @Android,
                            @iOS
                            Since this framework contains different apps for Android and iOS, always use tags to choose platform.
                            ''')
        string(name: 'FEATURE_CLASS', defaultValue: "src/test/resources/features/android/AndroidApkDemo.feature",
            description: '''Select feature class to execute. Examples:
                            src/test/resources/features/android/Demo.feature,
                            src/test/resources/features/android/Test.feature.
                            Leave this field empty if you want to run tags.
                            ''')
        string(name: 'forks', defaultValue: '1', description: 'Number of parallel threads')
        choice(name: 'device', choices: ['android_Pixel4_local', 'android_Nexus5X_local'], description: 'Tests run on exact device')
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