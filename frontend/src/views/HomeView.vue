<template>
  <Toast />
    <div class="row" style="margin-left:5%; margin-top:15%">
        <div>
            <Splitter style= "height:30rem; width:60rem; margin-left:40%; margin-bottom: 6em; margin-top: 0%; background-color: #F2E0F7; text-align:center">
                <SplitterPanel class="flex align-items-center justify-content-center">
                      <CadastroVue/>
                </SplitterPanel>
                <SplitterPanel class="flex align-items-center justify-content-center">
                    <LoginVue/>
                </SplitterPanel>
            </Splitter>
        </div>
    </div>
</template>

<script>
import Splitter from 'primevue/splitter';
import SplitterPanel from 'primevue/splitterpanel';
import Button from 'primevue/button';
import Dropdown from 'primevue/dropdown';
import Password from 'primevue/password';
import Divider from 'primevue/divider';
import Dialog from 'primevue/dialog';
import InputText from 'primevue/inputtext';
import CadastroVue from '@/components/Cadastro.vue';
import LoginVue from '@/components/Login.vue';
import Toast from 'primevue/toast';

export default{
        components: {
        Splitter,
        SplitterPanel,
        Button,
        Dropdown,
        Password,
        Divider,
        Dialog,
        InputText,
        Toast,
        CadastroVue,
        LoginVue
    }
};
</script>

<style lang="scss" scoped>
.form-demo {
    .card {
        min-width: 450px;

        form {
            margin-top: 2rem;
        }

        .field {
            margin-bottom: 1.5rem;
        }
    }

    @media screen and (max-width: 960px) {
        .card {
            width: 80%;
        }
    }
}

</style>
