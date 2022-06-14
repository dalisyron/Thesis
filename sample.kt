fun main(args: Array<String>) {
    val systemConfig = OffloadingSystemConfig(
            userEquipmentConfig = UserEquipmentConfig(
                stateConfig = UserEquipmentStateConfig(
                    taskQueueCapacity = 5,
                    tuNumberOfPackets = listOf(1, 3),
                    cpuNumberOfSections = listOf(7, 2),
                    numberOfQueues = 2
                ),
                componentsConfig = UserEquipmentComponentsConfig(
                    alpha = listOf(0.4, 0.9),
                    beta = 0.90,
                    etaConfig = null,
                    pTx = 1.0,
                    pLocal = 0.8,
                    pMax = 1.7
                )
            ),
            environmentParameters = EnvironmentParameters(
                nCloud = listOf(1, 1),
                tRx = 0.5,
            )
    )

    val optimalPolicy = RangedOptimalPolicyFinder.findOptimalPolicy(baseSystemConfig = systemConfig, precision = 10)
    /*
        // For multi-threaded execution use this instead:

        val optimalPolicy = ConcurrentRangedOptimalPolicyFinder(
            baseSystemConfig = systemConfig
        ).findOptimalPolicy(precision = 10, numberOfThreads = 8)
     */


    val decisionProbabilities: Map<StateAction, Double>
        = optimalPolicy.stochasticPolicyConfig.decisionProbabilities

    println(decisionProbabilities)
}
