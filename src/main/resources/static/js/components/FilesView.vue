<template>
    <ul>
        <tree-item
                class="item"
                :item="treeData"
        ></tree-item>
    </ul>
</template>

<script>
    import TreeItem from '../components/TreeItem.vue';

    export default {
        components: {
            TreeItem
        },
        name: "FilesView",
        data() {
            return {
                treeData: {},
                path: '',
                postfix: '',
                ssss: ''
            }
        },
        created() {
            this.$http.post('/files', {
                path: '/home/da-nizz/awdawdaw/',
                postfix: ''
            }).then(response => {
                    this.treeData = response.body;
                }, response => {
                    console.log(response.status)
                }
            )
        }
    }
</script>

<style scoped>

</style>