<template>
    <div class="row" style="margin-left:5%; margin-top:15%">
        <div>
        <!-- <div class="card" style="margin-left:5%; margin-bottom:5%"> -->
            <Splitter style= "height:30rem; width:60rem; margin-left:40%; margin-bottom: 6em; margin-top: 0%; background-color: #F2E0F7; text-align:center">
                <SplitterPanel class="flex align-items-center justify-content-center">
                      <div class="form-demo">
                        <Dialog v-model:visible="showMessage" :breakpoints="{ '960px': '80vw' }" :style="{ width: '30vw' }" position="top">
                            <div class="flex align-items-center flex-column pt-6 px-3">
                                <i class="pi pi-check-circle" :style="{fontSize: '5rem', color: 'var(--green-500)' }"></i>
                                <h5>...</h5>
                                <p :style="{lineHeight: 1.5, textIndent: '1rem'}">
                                    Your account is registered under name <b>{{name}}</b> ; it'll be valid next 30 days without activation. Please check <b>{{email}}</b> for activation instructions.
                                </p>
                            </div>
                            <template #footer>
                                <div class="flex justify-content-center">
                                    <Button label="OK" @click="toggleDialog" class="p-button-text" />
                                </div>
                            </template>
                        </Dialog>

                        <div class="flex justify-content-center">
                            <div class="card">
                                <h5 class="text-center">Register</h5>
                                <form @submit.prevent="handleSubmit(!v$.$invalid)" class="p-fluid">
                                    <div class="field">
                                        <div class="p-float-label">
                                            <InputText id="name" v-model="v$.name.$model" :class="{'p-invalid':v$.name.$invalid && submitted}" />
                                            <label for="name" :class="{'p-error':v$.name.$invalid && submitted}">Nome*</label>
                                        </div>
                                        <small v-if="(v$.name.$invalid && submitted) || v$.name.$pending.$response" class="p-error">{{v$.name.required.$message.replace('Value', 'Name')}}</small>
                                    </div>
                                    <div class="field">
                                        <div class="p-float-label p-input-icon-right">
                                            <i class="pi pi-envelope" />
                                            <InputText id="email" v-model="v$.email.$model" :class="{'p-invalid':v$.email.$invalid && submitted}" aria-describedby="email-error"/>
                                            <label for="email" :class="{'p-error':v$.email.$invalid && submitted}">E-mail*</label>
                                        </div>
                                        <span v-if="v$.email.$error && submitted">
                                            <span id="email-error" v-for="(error, index) of v$.email.$errors" :key="index">
                                            <small class="p-error">{{error.$message}}</small>
                                            </span>
                                        </span>
                                        <small v-else-if="(v$.email.$invalid && submitted) || v$.email.$pending.$response" class="p-error">{{v$.email.required.$message.replace('Value', 'Email')}}</small>
                                    </div>
                                    <div class="field">
                                        <div class="p-float-label">
                                            <Password id="password" v-model="v$.password.$model" :class="{'p-invalid':v$.password.$invalid && submitted}" toggleMask>
                                                <template #header>
                                                    <h6>Força da senha</h6>
                                                </template>
                                                <template #footer="sp">
                                                    {{sp.level}}
                                                    <Divider />
                                                    <p class="mt-2">Requisitos</p>
                                                    <ul class="pl-2 ml-2 mt-0" style="line-height: 1.5">
                                                        <li>Pelo menos uma minúscula</li>
                                                        <li>Pelo menos uma maiúscula</li>
                                                        <li>Pelo menos um numérico</li>
                                                        <li>Mínimo de 8 caracteres</li>
                                                    </ul>
                                                </template>
                                            </Password>
                                            <label for="password" :class="{'p-error':v$.password.$invalid && submitted}">Senha*</label>
                                        </div>
                                        <small v-if="(v$.password.$invalid && submitted) || v$.password.$pending.$response" class="p-error">{{v$.password.required.$message.replace('Value', 'Password')}}</small>
                                    </div>
                                    <router-link to="/dashboard">
                                       <Button type="submit" label="Cadastrar" class="mt-2" />
                                     </router-link>
                                    
                                </form>
                            </div>
                        </div>
                    </div>

                </SplitterPanel>
                <SplitterPanel class="flex align-items-center justify-content-center">
                    <div class="form-demo">
                        <Dialog v-model:visible="showMessage" :breakpoints="{ '960px': '80vw' }" :style="{ width: '30vw' }" position="top">
                            <div class="flex align-items-center flex-column pt-6 px-3">
                                <i class="pi pi-check-circle" :style="{fontSize: '5rem', color: 'var(--green-500)' }"></i>
                                <h5>Cadastrado com Sucesso!</h5>
                                <p :style="{lineHeight: 1.5, textIndent: '1rem'}">
                                    Sua conta está registrada em nome <b>{{name}}</b> ; será válido nos próximos 30 dias sem ativação. por favor, verifique <b>{{email}}</b> para instruções de ativação.
                                </p>
                            </div>
                            <template #footer>
                                <div class="flex justify-content-center">
                                     <Button label="OK" @click="toggleDialog" class="p-button-text" />
                                </div>
                            </template>
                        </Dialog>

                        <div class="flex justify-content-center">
                            <div class="card">
                                <h5 class="text-center">Login</h5>
                                <form @submit.prevent="handleSubmit(!v$.$invalid)" class="p-fluid">
                                    <div class="field">
                                        <div class="p-float-label p-input-icon-right">
                                            <i class="pi pi-envelope" />
                                            <InputText id="emailLogin" v-model="v$.email.$model" :class="{'p-invalid':v$.email.$invalid && submitted}" aria-describedby="email-error"/>
                                            <label for="emailLogin" :class="{'p-error':v$.email.$invalid && submitted}">Email*</label>
                                        </div>
                                        <span v-if="v$.email.$error && submitted">
                                            <span id="email-error" v-for="(error, index) of v$.email.$errors" :key="index">
                                            <small class="p-error">{{error.$message}}</small>
                                            </span>
                                        </span>
                                        <small v-else-if="(v$.email.$invalid && submitted) || v$.email.$pending.$response" class="p-error">{{v$.email.required.$message.replace('Value', 'Email')}}</small>
                                    </div>
                                    <div class="field">
                                        <div class="p-float-label">
                                            <Password id="passwordLogin" v-model="v$.password.$model" :class="{'p-invalid':v$.password.$invalid && submitted}" toggleMask>
                                            </Password>
                                            <label for="passwordLogin" :class="{'p-error':v$.password.$invalid && submitted}">Senha*</label>
                                        </div>
                                        <small v-if="(v$.password.$invalid && submitted) || v$.password.$pending.$response" class="p-error">{{v$.password.required.$message.replace('Value', 'Password')}}</small>
                                    </div>
                                     <router-link to="/dashboard">
                                       <Button type="submit" label="Entrar" class="mt-2"/>
                                     </router-link>
                                    
                                </form>
                            </div>
                        </div>
                    </div>

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
//import CountryService from './service/CountryService';
import { email, required } from "@vuelidate/validators";
import { useVuelidate } from "@vuelidate/core";

// import CountryService from './service/CountryService';

export default{
        name: "Dashboard",
        components:{
        Splitter,
        SplitterPanel,
        Button,
        Dropdown,
        Password,
        Divider,
        Dialog,
        InputText
    },
setup: () => ({ v$: useVuelidate() }),
    data() {
        return {
            name: '',
            email: '',
            password: '',
            date: null,
            country: null,
            accept: null,
            submitted: false,
            countries: null,
            showMessage: false
        }
    },
    countryService: null,
    validations() {
        return {
            name: {
                required
            },
            email: {
                required,
                email
            },
            password: {
                required
            },
            accept: {
                required
            }
        }
    },
    created() {
        //this.countryService = new CountryService();
    },
    mounted() {
        //this.countryService.getCountries().then(data => this.countries = data);
    },
    methods: {
        handleSubmit(isFormValid) {
            this.submitted = true;

            if (!isFormValid) {
                return;
            }

            this.toggleDialog();
        },
        toggleDialog() {
            this.showMessage = !this.showMessage;
        
            if(!this.showMessage) {
                this.resetForm();
            }
        },
        resetForm() {
            this.name = '';
            this.email = '';
            this.password = '';
            this.date = null;
            this.country = null;
            this.accept = null;
            this.submitted = false;
        }
    },
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
