package dev.villanueva.userland_utility.products.xppen.deco_pro

import dev.villanueva.userland_utility.products.ProductSingleSideShortcutsView

class DecoProSmallView : ProductSingleSideShortcutsView() {
    private val myController: DecoProSmallController by inject()

    init {
        super.controller = myController
        setup()
    }
}
