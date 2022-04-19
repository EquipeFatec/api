import { createApp, VueElement } from 'vue'
import App from './App.vue'
import PrimeVue from 'primevue/config';
import 'primevue/resources/themes/saga-purple/theme.css'       //theme
import 'primevue/resources/primevue.min.css'                //core css
import 'primeicons/primeicons.css'                           //icons
import router from './router'
//import store from './store'
import axios from 'axios'
import ToastService from 'primevue/toastservice'
axios.defaults.baseURL = 'http://localhost:8081/'

//vue.config.productionTIP = false

// new VueElement({
//      router,
//      store,
//      render: h =>(App)
//  }) .$mount('#app')

createApp(App)
    .use(router)
    .use(PrimeVue)
    .use(ToastService)
    .mount('#app');