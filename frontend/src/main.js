import { createApp } from 'vue'
import App from './App.vue'
import PrimeVue from 'primevue/config';
import 'primevue/resources/themes/saga-purple/theme.css'       //theme
import 'primevue/resources/primevue.min.css'                //core css
import 'primeicons/primeicons.css'                           //icons
import router from './router'

createApp(App)
    .use(router)
    .use(PrimeVue)
    .mount('#app');