<template>
  <Toast />
    <div class="row">
        <div>
            <div class="menu">
              <Button class="menu-button" @click="openModalUser">
                <img src="../assets/user.png" class="menu-image"/>
              </Button>
              <Button class="menu-button" @click="openModal">
                <img src="../assets/upload.png" class="menu-image"/>
              </Button>
              <Button class="menu-button" @click="download">
                <img src="../assets/download.png" class="menu-image"/>
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
                          <InputText placeholder="E-mail" />
                        </div>
                        <br>
                        <div class="p-inputgroup">
                          <span class="p-inputgroup-addon">
                              <i class="pi pi-phone"></i>
                          </span>
                          <InputText placeholder="Contato" />
                        </div>
                        <br>
                        <div class="p-inputgroup">
                          <span class="p-inputgroup-addon">
                              <i class="pi pi-lock"></i>
                          </span>
                          <InputText placeholder="Password" />
                        </div>
                        <br>
                        <br>
                        <span class="p-buttonset">
                             <Button label="Save" icon="pi pi-check" />
                        </span>
                    </div>
                    </div>

                </SplitterPanel>
      </Splitter>
          
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
import Password from 'primevue/password';
import Avatar from 'primevue/avatar';
import AvatarGroup from 'primevue/avatargroup';

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
      Password,
      Avatar,
      AvatarGroup     

  },
  data() {
		return {
			displayModal: false,
      displayModalUser: false,
      checked1: false,
      checked2: false,
      radioValue1: '',
     radioValue2: ''
		}
	},
  methods: {
    openModal() {
      this.displayModal = true;
    },
    openModalUser() {
      this.displayModalUser = true;
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
    }
  }
}
</script>

<style>
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

</style>