dependencies {
    compileOnly(project(":agent"))

    testImplementation(project(":agent"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.2.0")
    testImplementation("org.assertj:assertj-core:3.11.0")
}

tasks.named<Test>("test").configure {
    useJUnitPlatform()

    jvmArgumentProviders.add(AddJavaAgent(configurations.compileOnly))
}

class AddJavaAgent(
        @get:Classpath
        val agentConfiguration: FileCollection
) : CommandLineArgumentProvider {
    override fun asArguments(): Iterable<String> = listOf("-javaagent:" + agentConfiguration.singleFile.absolutePath)
}