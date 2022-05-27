<template>
  <Toast />
    <div class="row">
        <div>
            <div class="menu">
              <Button class="menu-button" @click="openModal">
                <img src="../assets/upload.png" class="menu-image"/>
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

</template>

<script>

import Dialog from 'primevue/dialog';
import Button from 'primevue/button';
import FileUpload from 'primevue/fileupload';
import axios from "axios";
import Toast from 'primevue/toast';

export default {
  name: 'Menu',
  components: {
      Dialog,
      Button,
      FileUpload,
      Toast
  },
  data() {
		return {
			displayModal: false
		}
	},
  methods: {
    openModal() {
      this.displayModal = true;
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