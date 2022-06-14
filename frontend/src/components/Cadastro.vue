<template>
    <Toast /> <!-- Componente que exibe mensagens na tela -->
    <div class="form-demo"> <!-- Formulario de Cadastro -->
        <div class="flex justify-content-center">
            <div class="card">
                <h5 class="text-center">Register</h5>
                <form @submit.prevent="handleSubmit(!v$.$invalid)" class="p-fluid"> <!-- Submit faz o envio do formulario para o metodo handleSubmit se não for invalido -->
                    <div class="field">
                        <div class="p-float-label">
                            <InputText id="name" v-model="v$.name.$model"
                                :class="{ 'p-invalid': v$.name.$invalid && submitted }" /> <!-- Input para o nome/ Verifica se é invalido e se foi submetido, se sim, exibe mensagem de erro na tela -->
                            <label for="name" :class="{ 'p-error': v$.name.$invalid && submitted }">Nome*</label> <!-- Mesma coisa do submitted acima -->
                        </div>
                        <small v-if="(v$.name.$invalid && submitted) || v$.name.$pending.$response"
                            class="p-error">{{ v$.name.required.$message.replace('Value', 'Name') }}</small> <!-- Small deixa o texto menor-->
                    </div>
                    <div class="field">
                        <div class="p-float-label p-input-icon-right">
                            <i class="pi pi-envelope" />
                            <InputText id="email" v-model="v$.email.$model"
                                :class="{ 'p-invalid': v$.email.$invalid && submitted }" aria-describedby="email-error" /> <!-- Input para o email -->
                            <label for="email" :class="{ 'p-error': v$.email.$invalid && submitted }">E-mail*</label>
                        </div>
                        <span v-if="v$.email.$error && submitted"> <!-- Span exibido caso haja erro no form e tenha sido submetido-->
                            <span id="email-error" v-for="(error, index) of v$.email.$errors" :key="index">
                                <small class="p-error">{{ error.$message }}</small> <!-- Exibe mensagens de erro -->
                            </span>
                        </span>
                        <small v-else-if="(v$.email.$invalid && submitted) || v$.email.$pending.$response"
                            class="p-error">{{ v$.email.required.$message.replace('Value', 'Email') }}</small>
                    </div>
                    <div class="field">
                        <div class="p-float-label">
                            <Password id="password" v-model="v$.password.$model"
                                :class="{ 'p-invalid': v$.password.$invalid && submitted }" toggleMask> <!-- Campo de senha -->
                                <template #header>
                                    <h6>Força da senha</h6>
                                </template>
                                <template #footer="sp">
                                    {{ sp.level }}
                                    <Divider /> <!-- Linha de dividir a tela -->
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
                    <Button type="submit" label="Cadastrar" class="mt-2" /> <!-- Botão de cadastro -->
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

export default{
        components: { //define e inicializa os componentes que vão ser usados
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
                id: null,
                nome: this.name,
                email: this.email,
                senha: this.password
            } //inicializando um objeto e passando o valor do preenchimento dos campos para ele

            if (!isFormValid) {
                return;
            }

            axios.post("http://localhost:8081/cadastro/usuario", usuario).then(() => { //post envia dados para o back, no caso o usuario, o then define o que é feito depois de receber resposta do back
                this.$toast.add({severity: 'success', summary: 'Cadastrado realizado com Sucesso', detail: 'Verifique seu e-mail', life: 3000}); //Mensagem de confirmação de cadastro
                //severity define o tipo do toast(sucesso, erro, warning, etc), summary é o titulo, detail é a descrição
                this.enviarEmailConfirmacao(usuario) //chama metodo de enviar email passando o usuario
                this.resetForm(); //chama metodo de limpar o formulario
            })
            .catch(() => { //caso o back retorne uma exception, exibe mensagem de erro
                this.$toast.add({severity: 'error', summary: 'Erro', detail: 'Não foi possível realizar o cadastro. Tente novamente mais tarde.', life: 3000});
            })

        },
        resetForm() { //limpa formulario
            this.name = '';
            this.email = '';
            this.password = '';
            this.submitted = false;
        },
        enviarEmailConfirmacao(usuario) { //metodo de envio de email recebendo usuario
            let emailToSend = {
                email: usuario.email,
                assunto: "Hey Alexia - Confirmação de Cadastro",
                corpo: "Olá, " + usuario.nome + "! \n" +
                "Estamos enviando este e-mail para confirmar que seu cadastro foi realizado com sucesso. " +
                "Agora, basta acessar o sistema e fazer Login para utilizá-lo. \n\n" +
                "Atenciosamente, Equipe Hey Alexia"
            } //monta objeto do email com o email do usuario, assunto e o corpo
            axios.post("http://localhost:8081/cadastro/email", emailToSend); // envia requisicao de email para o back com o objeto do email
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