<template>
  <Menu></Menu>
  <div style="text-align: center; font-size: 40pt; text-shadow: 1px 1px 3px black">
    Hey Alexia
  </div>

  <div class="row" style="margin-left:10%; margin-top:4%">
    <div class="col-sm-4">
      <Card style="width: 25rem; height:25rem; margin-bottom: 6em; background-color: #F2E0F7">
    <template #title> Produtos Mais vendidos </template>
    <template #content>
      <div class="container">
        <div>
           <ul>
             <li v-for="item in itens"  @click="exibir(item)" v-bind:key="item.id">
               {{item.nome}}
             </li>
            </ul>
        </div> 
      </div>
    </template>
    </Card>
    </div> 
    <div class="col-sm-4">
     <Card style="width: 25rem; height: 25rem; margin-bottom: 5em; background-color: #F2E0F7">
      <template #title> Valor unitário (R$) </template>
    <template #content>
      <div class="container">
        <h1 v-bind:key="this.produtoExibido.id">{{this.produtoExibido.valor || ''}}</h1>
      </div>
    </template>
   </Card>
    </div>    
  </div>
</template>

<script>
import Menu from "./components/Menu.vue";
import Card from "primevue/card";
import axios from "axios";
import Button from "primevue/button"

export default {
  name: "App",
  components: {
    Menu,
    Card,
    Button
  },
  mounted () {
    axios
      .get('http://localhost:8081/produto')
      .then(response => {
        this.itens = response.data
      })    
  },
  methods:{
    exibir(produto){
      this.produtoExibido = produto
    }
  },
  data() {
    return {
      displayModal: false,
      itens: [],
      produtoExibido: {}
    };
  },
};
</script>

<style>
.container {
  margin: 0%;
  margin-left: 15%;
  max-width: 450px;
  font-style: italic;
  font-family:'Times New Roman', Times, serif;
  text-align: center;
    font-size: 20pt;
    display: flex;
  flex-flow: row wrap;
  justify-content: space-between;
}

.container ul {
  list-style-type: none;
  text-decoration-color: black;
  
}

.container ul li {
  margin-top: 7px;
  bottom: 70;
  top: 70;
  position: relative;
  padding-bottom: 3px;
  border-bottom: 1px solid #266fc5;
}
</style>