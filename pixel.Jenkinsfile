pipeline {

    agent {
        node("test-executor")
    }

    parameters {
        string(name: 'branch', defaultValue: 'master', description: 'Branch to checkout')
        string(name: 'TAGS', defaultValue: '@Android',
            description: '''Cucumber tags to be executed. Examples:
                            @Android,
                            "@Android and @Smoke",
                            @iOS
                            Since this framework contains different apps for Android and iOS, always use tags to choose platform.
                            ''')
        string(name: 'FEATURE_CLASS', defaultValue: "src/test/resources/features/android/AndroidApkDemo.feature",
            description: '''Select feature class to execute. Examples:
                            src/test/resources/features/android/Demo.feature,
                            src/test/resources/features/android/Test.feature.
                            In this particular framework use features with tags
                            ''')
        string(name: 'forks', defaultValue: '1', description: 'Number of parallel threads')
    }

    stages {

        stage('Execute tests'){
            steps {
                script {
                    bat "mvn clean test -Dcucumber.options=${FEATURE_CLASS} -Dcucumber.filter.tags=${TAGS} -Ddevice=android_Pixel4_local -Dforks=${params.forks}"
                }
            }
        }
    }

}