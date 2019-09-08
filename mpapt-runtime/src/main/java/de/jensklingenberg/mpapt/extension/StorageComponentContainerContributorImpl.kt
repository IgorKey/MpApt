package de.jensklingenberg.mpapt.extension

import de.jensklingenberg.mpapt.model.AbstractProcessor
import de.jensklingenberg.mpapt.utils.KotlinPlatformValues
import org.jetbrains.kotlin.container.StorageComponentContainer
import org.jetbrains.kotlin.container.useInstance
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.extensions.StorageComponentContainerContributor
import org.jetbrains.kotlin.platform.TargetPlatform

class StorageComponentContainerContributorImpl(val processor: AbstractProcessor) : StorageComponentContainerContributor {

    /**
     * Here we get the targetplatform of the module
     */
    override fun registerModuleComponents(container: StorageComponentContainer, platform: TargetPlatform, moduleDescriptor: ModuleDescriptor) {
        processor.activeTargetPlatform = platform

        val platformName= platform.first().platformName
        if(platformName.equals( KotlinPlatformValues.JVM )){
            container.useInstance(JVMCallChecker(processor))
        }
    }
}