<template>
    <Toast /> <!-- Componente que exibe mensagens na tela -->
    <div class="form-demo"> <!-- Formulario de Login -->
        <div class="flex justify-content-center">
            <div class="card">
                <h5 class="text-center">Login</h5>
                <form @submit.prevent="handleSubmit(!v$.$invalid)" class="p-fluid"> <!-- Submit faz o envio do formulario para o metodo handleSubmit se não for invalido -->
                    <div class="field">
                        <div class="p-float-label p-input-icon-right">
                            <i class="pi pi-envelope" />
                            <InputText id="emailLogin" v-model="v$.email.$model"
                                :class="{ 'p-invalid': v$.email.$invalid && submitted }" aria-describedby="email-error" /> <!-- Input para o email/ Verifica se é invalido e se foi submetido, se sim, exibe mensagem de erro na tela -->
                            <label for="emailLogin" :class="{ 'p-error': v$.email.$invalid && submitted }">E-mail*</label> <!-- Mesma coisa do submitted acima -->
                        </div>
                        <span v-if="v$.email.$error && submitted">
                            <span id="email-error" v-for="(error, index) of v$.email.$errors" :key="index">
                                <small class="p-error">{{ error.$message }}</small> <!-- Small deixa o texto menor-->
                            </span> <!-- Span exibido caso haja erro no email e tenha sido submetido-->
                        </span>
                        <small v-else-if="(v$.email.$invalid && submitted) || v$.email.$pending.$response"
                            class="p-error">{{ v$.email.required.$message.replace('Value', 'Email') }}</small>
                    </div>
                    <div class="field">
                        <div class="p-float-label">
                            <Password id="passwordLogin" v-model="v$.password.$model"
                                :class="{ 'p-invalid': v$.password.$invalid && submitted }" toggleMask>
                            </Password> <!-- Senha -->
                            <label for="passwordLogin" :class="{ 'p-error': v$.password.$invalid && submitted }">Senha*</label>
                        </div>
                        <small v-if="(v$.password.$invalid && submitted) || v$.password.$pending.$response"
                            class="p-error">{{ v$.password.required.$message.replace('Value', 'Password') }}</small>
                    </div>
                    <Button type="submit" label="Entrar" class="mt-2" /> <!--Botão de login-->
                </form>
            </div>
        </div>
    </div>

</template>

<script>

//Importando as bibliotecas e componentes
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
        components: { //define e inicializa componentes
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
setup: () => ({ v$: useVuelidate() }), //setup = define configuração, no caso define o uso do vuelidate, componente do Vue de validação
    data() { //dados
        return { //incializa variaveis ou objetos
            name: '',
            email: '',
            password: '',
            submitted: false,
            showMessage: false
        }
    },
    validations() { //definindo as validações que serão feitas
        return {
            name: {
                required //obrigatorio
            },
            email: {
                required,
                email //verifica formatação de email valido
            },
            password: {
                required
            }
        }
    },
    methods: { //métodos/funções
        handleSubmit(isFormValid) { //submetendo o formulario
            this.submitted = true;
            let usuario = {
                email: this.email,
                password: this.password
            } //colocando o valor preenchido nos campos da tela no objeto usuario

            // if (!isFormValid) {
            //     return;
            // }

            axios.post("http://localhost:8081/login", usuario).then(() => { //post envia dados para o back, no caso o usuario, o then define o que é feito depois de receber resposta do back
                let email = usuario.email //colocando email do usuario numa variavel
                router.push({ path: '/dashboard', query: { email } }) // chamando rota da dashboard, path é o caminho, a query esta recebendo o email para ser usado depois na busca do usuario
            })
            .catch(() => { //caso back retorne exception exibe mensagem de erro
                this.$toast.add({severity: 'error', summary: 'Erro', detail: 'Não foi possível realizar o login. Tente novamente mais tarde.', life: 3000});
            })

        },
        resetForm() { //limpa formulario
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