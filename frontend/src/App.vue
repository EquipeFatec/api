<template>
  <Menu></Menu>
  <div style="text-align: center; font-size: 40pt; text-shadow: 1px 1px 3px black">
    Hey Alexia
  </div>

  <div class="row" style="margin-left:18%; margin-top:3%">
    <table style="border: none;" cellspacing="15">
      <tr>
        <td>
          <Card style="width: 25rem; height:25rem; margin-bottom: 6em; background-color: #F2E0F7">
            <template #title> Produtos Mais vendidos </template>
            <template #content>
              <div class="container">
                <div>
                  <ul>
                    <li v-for="item in itens" @click="exibir(item)" v-bind:key="item.id">
                      {{ item.nome }}
                    </li>
                  </ul>
                </div>
              </div>
            </template>
          </Card>
        </td>
        <td>
          <Card style="width: 25rem; height: 25rem; margin-bottom: 6em; background-color: #F2E0F7">
            <template #title> Clientes </template>
            <template #content>
              <div class="container">
                <Chart type="pie" :data="chartData" :options="lightOptions" />
              </div>
            </template>
          </Card>
        </td>
      </tr>
    </table>
  </div>

</template>

<script>
import Menu from "./components/Menu.vue";
import Card from "primevue/card";
import axios from "axios";
import Button from "primevue/button";
import Chart from "primevue/chart";

export default {
  name: "App",
  components: {
    Menu,
    Card,
    Button,
    Chart
  },
  mounted() {
    axios.get("http://localhost:8081/produto").then((response) => {
      this.itens = response.data;
    });
  },
  methods: {
    exibir(produto) {
      this.produtoExibido = produto;
    },
  },
  data() {
    return {
      displayModal: false,
      itens: [],
      produtoExibido: {},
      chartData: {
        labels: ['Feminino', 'Masculino'],
        datasets: [
          {
            data: [63, 33],
            backgroundColor: ["#AF72B0", "#2D8BBA"],
            hoverBackgroundColor: ["#B469B4", "#477EBF"]
          }
        ]
      },
      lightOptions: {
        plugins: {
          legend: {
            labels: {
              color: '#495057'
            }
          }
        }
      }
    };
  }
};
</script>

<style>
.container {
  margin: 0%;
  max-width: 450px;
  font-style: italic;
  font-family: "Times New Roman", Times, serif;
  text-align: center;
  font-size: 20pt;
  display: flex;
  flex-flow: row wrap;
  justify-content: center;
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