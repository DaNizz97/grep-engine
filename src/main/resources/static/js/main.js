import Vue from 'vue'
import App from 'pages/App.vue'
import VueResource from 'vue-resource'
import Vuetify from 'vuetify'

Vue.use(Vuetify);
Vue.use(VueResource);

new Vue({
    el: '#app',
    render: a => a(App)
});