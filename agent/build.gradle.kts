import java.util.concurrent.Callable

val jar = tasks.named<Jar>("jar")

jar.configure {
    manifest {
        attributes(
                "Agent-Class" to "agent.LambdaInspectorAgent",
                "Premain-Class" to "agent.LambdaInspectorAgent",
                "Can-Retransform-Classes" to "true"
        )
    }
    from(Callable { configurations.compileOnly.map { zipTree(it) } })
}


dependencies {
    compileOnly("org.ow2.asm:asm:6.2.1")
    compileOnly("org.ow2.asm:asm-analysis:6.2.1")
}
