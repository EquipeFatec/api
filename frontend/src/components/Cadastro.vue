<template>
    <Toast />
    <div class="form-demo">
        <div class="flex justify-content-center">
            <div class="card">
                <h5 class="text-center">Register</h5>
                <form @submit.prevent="handleSubmit(!v$.$invalid)" class="p-fluid">
                    <div class="field">
                        <div class="p-float-label">
                            <InputText id="name" v-model="v$.name.$model"
                                :class="{ 'p-invalid': v$.name.$invalid && submitted }" />
                            <label for="name" :class="{ 'p-error': v$.name.$invalid && submitted }">Nome*</label>
                        </div>
                        <small v-if="(v$.name.$invalid && submitted) || v$.name.$pending.$response"
                            class="p-error">{{ v$.name.required.$message.replace('Value', 'Name') }}</small>
                    </div>
                    <div class="field">
                        <div class="p-float-label p-input-icon-right">
                            <i class="pi pi-envelope" />
                            <InputText id="email" v-model="v$.email.$model"
                                :class="{ 'p-invalid': v$.email.$invalid && submitted }" aria-describedby="email-error" />
                            <label for="email" :class="{ 'p-error': v$.email.$invalid && submitted }">E-mail*</label>
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
                            <Password id="password" v-model="v$.password.$model"
                                :class="{ 'p-invalid': v$.password.$invalid && submitted }" toggleMask>
                                <template #header>
                                    <h6>Força da senha</h6>
                                </template>
                                <template #footer="sp">
                                    {{ sp.level }}
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
                            <label for="password" :class="{ 'p-error': v$.password.$invalid && submitted }">Senha*</label>
                        </div>
                        <small v-if="(v$.password.$invalid && submitted) || v$.password.$pending.$response"
                            class="p-error">{{ v$.password.required.$message.replace('Value', 'Password') }}</small>
                    </div>
                    <Button type="submit" label="Cadastrar" class="mt-2" />
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
                id: null,
                nome: this.name,
                email: this.email,
                senha: this.password
            }

            if (!isFormValid) {
                return;
            }

            axios.post("http://localhost:8081/cadastro/usuario", usuario).then(() => {
                this.$toast.add({severity: 'success', summary: 'Cadastrado realizado com Sucesso', detail: 'Verifique seu e-mail', life: 3000});
                this.enviarEmailConfirmacao(usuario)
                this.resetForm();
            })
            .catch(() => {
                this.$toast.add({severity: 'error', summary: 'Erro', detail: 'Não foi possível realizar o cadastro. Tente novamente mais tarde.', life: 3000});
            })

        },
        resetForm() {
            this.name = '';
            this.email = '';
            this.password = '';
            this.submitted = false;
        },
        enviarEmailConfirmacao(usuario) {
            let emailToSend = {
                email: usuario.email,
                assunto: "Hey Alexia - Confirmação de Cadastro",
                corpo: "Olá, " + usuario.nome + "! \n" +
                "Estamos enviando este e-mail para confirmar que seu cadastro foi realizado com sucesso. " +
                "Agora, basta acessar o sistema e fazer Login para utilizá-lo. \n\n" +
                "Atenciosamente, Equipe Hey Alexia"
            }
            axios.post("http://localhost:8081/cadastro/email", emailToSend);
        }
    },
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