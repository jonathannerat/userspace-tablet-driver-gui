package dev.villanueva.userland_utility.products

import dev.villanueva.userland_utility.products.config.DeviceConfiguration
import dev.villanueva.userland_utility.products.xppen.deco.Deco01v2Controller
import dev.villanueva.userland_utility.products.xppen.deco.Deco01v2View
import dev.villanueva.userland_utility.products.xppen.deco_pro.DecoProSmallController
import dev.villanueva.userland_utility.products.xppen.deco_pro.DecoProSmallView
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

object SupportedProducts {
    private val productToClassMap: MutableMap<String, KClass<out ProductView>> = HashMap()
    private val nameToProductIdMap: MutableMap<String, String> = HashMap()
    private val productIdToName: MutableMap<String, String> = HashMap()
    private val viewDeviceConfigurationMap: MutableMap<String, KProperty1<out ProductView, DeviceConfiguration?>> = HashMap()

    init {
        // Deco Pro Small
        productToClassMap[DecoProSmallController.getProductName()] = DecoProSmallView::class
        nameToProductIdMap[DecoProSmallController.getProductName()] = DecoProSmallController.getVendorProductString()
        productIdToName[DecoProSmallController.getVendorProductString()] = DecoProSmallController.getProductName()
        viewDeviceConfigurationMap[DecoProSmallController.getProductName()] = DecoProSmallView::deviceConfiguration

        // Deco 01v2
        productToClassMap[Deco01v2Controller.getProductName()] = Deco01v2View::class
        nameToProductIdMap[Deco01v2Controller.getProductName()] = DecoProSmallController.getVendorProductString()
        productIdToName[Deco01v2Controller.getVendorProductString()] = Deco01v2Controller.getProductName()
        viewDeviceConfigurationMap[Deco01v2Controller.getProductName()] = Deco01v2View::deviceConfiguration
    }

    fun getViewForProduct(product: String): KClass<out ProductView>? {
        return productToClassMap[product]
    }

    fun getProductIdFromName(name: String): String {
        return nameToProductIdMap[name] ?: "0000:0000"
    }

    fun getNameOfProduct(vendorId: Short, productId: Short): String {
        return productIdToName["$vendorId:$productId"] ?: "Unknown device"
    }

    fun getConfigPropertyForProduct(deviceName: String): KProperty1<out ProductView, DeviceConfiguration?> {
        return viewDeviceConfigurationMap[deviceName]!!
    }
}