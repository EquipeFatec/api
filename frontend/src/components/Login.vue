<template>
    <Toast />
    <div class="form-demo">
        <div class="flex justify-content-center">
            <div class="card">
                <h5 class="text-center">Login</h5>
                <form @submit.prevent="handleSubmit(!v$.$invalid)" class="p-fluid">
                    <div class="field">
                        <div class="p-float-label p-input-icon-right">
                            <i class="pi pi-envelope" />
                            <InputText id="emailLogin" v-model="v$.email.$model"
                                :class="{ 'p-invalid': v$.email.$invalid && submitted }" aria-describedby="email-error" />
                            <label for="emailLogin" :class="{ 'p-error': v$.email.$invalid && submitted }">E-mail*</label>
                        </div>
                        <span v-if="v$.email.$error && submitted">
                            <span id="email-error" v-for="(error, index) of v$.email.$errors" :key="index">
                                <small class="p-error">{{ error.$message }}</small>
                            </span>
                        </span>
                        <small v-else-if="(v$.email.$invalid && submitted) || v$.email.$pending.$response"
                            class="p-error">{{ v$.email.required.$message.replace('Value', 'Email') }}</small>
                    </div>
                    <div class="field">
                        <div class="p-float-label">
                            <Password id="passwordLogin" v-model="v$.password.$model"
                                :class="{ 'p-invalid': v$.password.$invalid && submitted }" toggleMask>
                            </Password>
                            <label for="passwordLogin" :class="{ 'p-error': v$.password.$invalid && submitted }">Senha*</label>
                        </div>
                        <small v-if="(v$.password.$invalid && submitted) || v$.password.$pending.$response"
                            class="p-error">{{ v$.password.required.$message.replace('Value', 'Password') }}</small>
                    </div>
                    <Button type="submit" label="Entrar" class="mt-2" />
                </form>
            </div>
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
import Toast from 'primevue/toast';
import { email, required } from "@vuelidate/validators";
import { useVuelidate } from "@vuelidate/core";
import axios from 'axios';
import router from '@/router';

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
        Toast
    },
setup: () => ({ v$: useVuelidate() }),
    data() {
        return {
            name: '',
            email: '',
            password: '',
            submitted: false,
            showMessage: false
        }
    },
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
            }
        }
    },
    methods: {
        handleSubmit(isFormValid) {
            this.submitted = true;
            let usuario = {
                email: this.email,
                password: this.password
            }

            // if (!isFormValid) {
            //     return;
            // }

            axios.post("http://localhost:8081/login", usuario).then(() => {
                let email = usuario.email
                router.push({ path: '/dashboard', query: { email } })
            })
            .catch(() => {
                this.$toast.add({severity: 'error', summary: 'Erro', detail: 'Não foi possível realizar o login. Tente novamente mais tarde.', life: 3000});
            })

        },
        resetForm() {
            this.name = '';
            this.email = '';
            this.password = '';
            this.submitted = false;
        }
    }
}

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