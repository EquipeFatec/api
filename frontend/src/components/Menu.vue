<template>
  <Toast />
    <div class="row">
        <div>
            <div class="menu">
              <Button class="menu-button" @click="openModalUser" v-tooltip="'Minha Conta'">
                <img src="../assets/user.png" class="menu-image"/>
              </Button>
              <Button class="menu-button" @click="openModal" v-tooltip="'Upload'">
                <img src="../assets/upload.png" class="menu-image"/>
              </Button>
              <!-- <Button class="menu-button" @click="download">
                <img src="../assets/download.png" class="menu-image"/>
              </Button> -->
              <Button class="menu-button" @click="openModalManual" v-tooltip="'Manual'">
                <img src="../assets/icon_manual.png" class="menu-image"/>
              </Button>
            </div>
         </div>
    </div>

  <Dialog header="Upload de Arquivos" v-model:visible="displayModal" :style="{width: '50vw'}" :modal="true">
      <FileUpload name="csv" :customUpload="true" @uploader="upload" :multiple="false" accept=".txt" :maxFileSize="1000000">
          <template #empty>
            <p>Arraste e solte arquivos para Upload.</p>
          </template>
      </FileUpload>
  </Dialog>
  <Dialog header="Minha Conta" v-model:visible="displayModalUser" :style="{width: '50vw'}" :modal="true">
      <Splitter style="height: 300px" class="mb-5">

                <SplitterPanel class="flex align-items-center justify-content-center">
                    <div class="grid p-fluid" style="margin-left:40%; margin-top:40%">
                           <Avatar icon="pi pi-user" class="mr-20" size="large" style="background-color:#9C27B0; color: #ffffff; align-items: center;" shape="circle" />
                    </div>
                </SplitterPanel>
                <SplitterPanel class="flex align-items-center justify-content-center">
                   <div class="grid p-fluid">
                      <div class="col-12 md:col-4">
                        <br>
                        <div class="p-inputgroup">
                          <span class="p-inputgroup-addon">
                              <i class="pi pi-envelope"></i>
                          </span>
                          <InputText placeholder="E-mail" v-model="email" :value=user.email>{{user.email}}</InputText>
                        </div>
                        <br>
                        <div class="p-inputgroup">
                          <span class="p-inputgroup-addon">
                              <i class="pi pi-user"></i>
                          </span>
                          <InputText placeholder="Nome" v-model="nome" :value=user.nome>{{user.nome}}</InputText>
                        </div>
                        <br>
                        <div class="p-inputgroup">
                          <span class="p-inputgroup-addon">
                              <i class="pi pi-lock"></i>
                          </span>
                          <InputText type="password" placeholder="Password" v-model="senha" :value=user.senha>{{user.senha}}</InputText>
                        </div>
                        <br>
                        <br>
                        <span class="p-buttonset">
                             <Button label="Save" icon="pi pi-check" @click="updateUser(this.user)"/>
                        </span>
                    </div>
                    </div>

                </SplitterPanel>
      </Splitter>
          
    </Dialog>
    <Dialog header="Manual do usuário" v-model:visible="displayModalManual" :style="{width: '50vw'}" :modal="true">
        <div class="grid">
            <div class="col-12 md:col-4 p-5">
                <ScrollPanel style="width: 100%; height: 200px">
                        <h3><b>1.Inserção de dados</b></h3>
                        A inserção de dados tem como objetivo popular o sistema para que seja possível a visualização dos dados através dos gráficos e análises presentes no sistema. Para realizar a inserção de dados, o usuário deve:
                        <p>1.1	No menu lateral direito, clicar no ícone “Inserir CSV”, botão “Choose” em seguida selecionar o arquivo CSV desejado, após a escolha do CSV, clicar no botão Abrir e posteriormente no botão “Upload“.</p>
                        <p>1.2	 No menu lateral direito, clicar no ícone “Inserir CSV”, abrir o local onde o arquivo CSV se encontra, em seguida arrastar o arquivo CSV para a área indicada “Arraste e solte arquivos para Upload” e posteriormente no botão “Upload“.</p>
                        
                        <h3><b>2.Exportação de Relatórios</b></h3>
                        A exportação de relatórios tem como objetivo permitir que o usuário tenha os dados do sistema em um arquivo “CSV” para o fim que desejar. Para realizar esta exportação, o usuário deve:
                        No menu lateral direito,
                        
                        <h3><b>3.Dados do usuário</b></h3>
                        A exibição dos dados do usuário tem como objetivo permitir que o usuário altere seus dados cadastrais como E-mail, Telefone e Senha. Para realizar as alterações citadas acima o usuário deve:
                        <p>3.1 No menu lateral direito, clicar no ícone “Meus dados”, para alterar a imagem do usuário, clique no ícone “Alterar imagem” em seguida clique no botão “Salvar”.</p>
                        <p>3.2 No menu lateral direito, clicar no ícone “Meus dados”, para alterar o e-mail do usuário, clique no campo “E-mail”, digite o novo endereço eletrônico, em seguida clique no botão “Salvar”.</p>
                        <p>3.2 No menu lateral direito, clicar no ícone “Meus dados”, para alterar o telefone do usuário, clique no campo “Telefone”, digite o novo número, em seguida clique no botão “Salvar”.</p>
                        <p>3.3 No menu lateral direito, clicar no ícone “Meus dados”, para alterar a senha do usuário, clique no campo “Senha”, digite a nova credencial de acesso, em seguida clique no botão “Salvar”.</p>

                   
                </ScrollPanel>
            </div>
         </div>
    </Dialog> 

</template>

<script>

import Dialog from 'primevue/dialog';
import Button from 'primevue/button';
import FileUpload from 'primevue/fileupload';
import axios from "axios";
import Toast from 'primevue/toast';
import {PrimeIcons} from 'primevue/api';
import InputText from 'primevue/inputtext';
import Splitter from 'primevue/splitter';
import SplitterPanel from 'primevue/splitterpanel';
import Avatar from 'primevue/avatar';
import AvatarGroup from 'primevue/avatargroup';
import Password from 'primevue/password';
import Tooltip from 'primevue/tooltip';

export default {
  name: 'Menu',
  components: {
      Dialog,
      Button,
      FileUpload,
      Toast,
      PrimeIcons,
      InputText,
      Splitter,
      SplitterPanel,
      Avatar,
      AvatarGroup,
      Password
  },
  props: {email: String},
  data() {
		return {
			displayModal: false,
      displayModalUser: false,
      displayModalManual: false,
      checked1: false,
      checked2: false,
      radioValue1: '',
     radioValue2: '',
     email: this.email,
     user:{
       id:null,
       nome:'',
       email:'',
       senha:''
     }
      
		}
	},
  methods: {
    openModal() {
      this.displayModal = true;
    },
    openModalUser() {
      this.displayModalUser = true;
      this.exibir(this.email);
    },
    openModalManual(){
      this.displayModalManual = true;

    },
    exibir(email){
      axios.get("http://localhost:8081/cadastro/findbyemail/"+email)
        .then((response)=>{
            this.user=response.data;
            console.log(response.data)
      })
    },
    upload(event){
      console.log(event.files[0])
      let formData = new FormData()
      formData.append('file', event.files[0])
      axios.post("http://localhost:8081/upload/cliente", formData, {
        headers: {
           "Content-Type": "multipart/form-data",
         }
      }).then(() => {
        this.$toast.add({severity: 'success', summary: 'Sucesso', detail: 'Upload Concluído', life: 3000});
      })
      .catch(() => {
        this.$toast.add({severity: 'error', summary: 'Erro', detail: 'Erro ao importar arquivo', life: 3000});
      })
    },
    updateUser(user){
      user.email = this.email
      user.nome = this.nome
      //ToDo: ver por que que a senha não está funcionando pra editar
      //user.senha = this.senha
      debugger
        axios.put("http://localhost:8081/cadastro", user)
        .then((response)=>{
            this.$toast.add({severity: 'success', summary: 'Usuário alterado com sucesso', life: 3000});
        })
        .catch(() => {
            this.$toast.add({severity: 'error', summary: 'Erro ao editar usuário', life: 3000});
        })
    }
  },
  directives: {
    'tooltip': Tooltip
  }
}
</script>

<style lang="scss" scoped>
.menu-container {
  z-index: 1000;
  position: fixed;
  left: 277px;
  width: 277px;
  height: 100%;
  margin-left: -277px;
  overflow-y: auto;
  background: #000;
  top: 0;
  overflow: hidden;
  transition: all .5s ease;
}

.menu {
  position: fixed;
  width: 60px;
  height: 100%;
  background: #9C27B0;
  box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
}

.menu-image {
  width: 40px;
  height: 40px;
}

.menu-button {
  width: 50px !important;
  height: 50px !important;
  border: none !important;
  padding: 10% !important;
  margin: 7.5% !important;
  cursor: pointer;
}

.confirmation-content {
    display: flex;
    align-items: center;
    justify-content: center;
}

.p-dialog .p-button {
    min-width: 6rem;
}

::v-deep(.p-scrollpanel) 
    p {
        padding: .5rem;
        line-height: 1.5;
        margin: 0;
    }

.custombar1 {
    .p-scrollpanel-wrapper {
      border-right: 9px solid var(--surface-ground);
    }

    .p-scrollpanel-bar {
        background-color: var(--primary-color);
        opacity: 1;
        transition: background-color .2s;

        :hover {
          background-color: #007ad9;
        }
    }
}

.custombar2 {
    .p-scrollpanel-wrapper {
        border-right: 9px solid var(--surface-border);
        border-bottom: 9px solid var(--surface-border);
    }

    .p-scrollpanel-bar {
        background-color: var(--surface-ground);
        border-radius: 0;
        opacity: 1;
        transition: background-color .2s;
    }
}  

    
</style>