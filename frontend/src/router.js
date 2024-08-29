
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import OrderOrderManager from "./components/listers/OrderOrderCards"
import OrderOrderDetail from "./components/listers/OrderOrderDetail"

import SupportDeliveryManager from "./components/listers/SupportDeliveryCards"
import SupportDeliveryDetail from "./components/listers/SupportDeliveryDetail"
import SupportInventoryManager from "./components/listers/SupportInventoryCards"
import SupportInventoryDetail from "./components/listers/SupportInventoryDetail"



export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/orders/orders',
                name: 'OrderOrderManager',
                component: OrderOrderManager
            },
            {
                path: '/orders/orders/:id',
                name: 'OrderOrderDetail',
                component: OrderOrderDetail
            },

            {
                path: '/supports/deliveries',
                name: 'SupportDeliveryManager',
                component: SupportDeliveryManager
            },
            {
                path: '/supports/deliveries/:id',
                name: 'SupportDeliveryDetail',
                component: SupportDeliveryDetail
            },
            {
                path: '/supports/inventories',
                name: 'SupportInventoryManager',
                component: SupportInventoryManager
            },
            {
                path: '/supports/inventories/:id',
                name: 'SupportInventoryDetail',
                component: SupportInventoryDetail
            },




    ]
})
