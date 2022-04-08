<template>
  <div class="row">
      <div>
          <div class="menu">
            <Button class="menu-button" @click="openModal">
              <img src="../assets/upload.png" class="menu-image"/>
            </Button>
          </div>
      </div>
  </div>

  <Dialog header="Header" v-model:visible="displayModal" :style="{width: '50vw'}" :modal="true">
      <p class="m-0">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
      <!-- <template #footer>
          <Button label="No" icon="pi pi-times" @click="closeModal" class="p-button-text"/>
          <Button label="Yes" icon="pi pi-check" @click="closeModal" autofocus />
      </template> -->
  </Dialog>

</template>

<script>

import Dialog from 'primevue/dialog';
import Button from 'primevue/button';

export default {
  name: 'Menu',
  components: {
      Dialog,
      Button
  },
  data() {
		return {
			displayModal: false
		}
	},
  methods: {
    openModal() {
            this.displayModal = true;
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