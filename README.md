
Unit Testing in Android with a sample application


    Add dependencies in your app level build.gradle file:

       testImplementation 'junit:junit:4.12'
       testImplementation 'org.mockito:mockito-core:1.10.19'



       Junit: It is a “Unit Testing” framework for Java Applications. annotations          such as @Test, @Before, @After etc.
       Mockito: Mockito mocks (or fakes) the dependencies required by the class being        tested. It provides annotations such as @Mock.
