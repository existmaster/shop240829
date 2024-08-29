<template>

    <v-data-table
        :headers="headers"
        :items="view"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ViewView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "customerId", value: "customerId" },
                { text: "productId", value: "productId" },
                { text: "qty", value: "qty" },
                { text: "address", value: "address" },
                { text: "orderStatus", value: "orderStatus" },
                { text: "deliveryStatus", value: "deliveryStatus" },
            ],
            view : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/views'))

            temp.data._embedded.views.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.view = temp.data._embedded.views;
        },
        methods: {
        }
    }
</script>

