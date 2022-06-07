<template>
  <Menu :email="this.emailUsuario"></Menu>
  <div style="margin-left:115%; width:350px">
      <div style="font-size: 40pt; text-shadow: 1px 1px 3px black;"> Hey Alexia </div>
  </div>

  <div class="row" style="margin-left:15%; margin-top:2%">
    <table style="border: none;" cellspacing="15">
      <tr>
        <td rowspan="2">
          <Card style="width: 25rem; height:30rem; margin-bottom: 6em; margin-top: 0%;  background-color: #F2E0F7; text-align:center">
            <template #title> Produtos mais vendidos </template>
            <template #content>
              <div>
                <div class="card">
                  <DataTable :value="itens" responsiveLayout="scroll">
                    <Column field="0" header="Nome" :sortable="true"></Column>
                    <Column field="1" header="Quantidade" :sortable="true"></Column>
                    <Column field="2" header="Valor Total" :sortable="true"></Column>
                  </DataTable>
                </div>
              </div>
            </template>
          </Card>
          <Card style="width: 25rem; height:20rem; margin-bottom: 10em; margin-top: 0%; background-color: #F2E0F7; text-align:center" >
            <template #title> Clientes </template>
            <template #content>
              <div>
                <div class="card" style="height:7rem" >
                  <SelectButton v-model="value1" :options="options" :change="selectChange()" />
                    <p></p>
                  <Chart type="bar" :data="dataBarChart" :options="optionsBarChart"/>
                  </div>
          
                </div>
                </template>
          </Card>
        </td>
        <td>
          <Card style="width: 25rem; height: 28rem; background-color: #F2E0F7; text-align:center">
            <template #title> Produtos mais vendidos (%) </template>
            <template #content>
              <div class="container">
                <Chart type="pie" :data="chartData" :options="lightOptions" />
              </div>
            </template>
          </Card>
        </td>
        <td>
          <Card style="width: 25rem; height:28rem; background-color: #F2E0F7; text-align:center">
            <template #title> Análise Mensal </template>
            <template #content>
              <br /><br /><br />
              <label class="customlabel">TOTAL R$</label>
              <br />
              <label class="bigtitle">R$ {{ analiseValorTotal }}</label>
              <hr />
              <label class="customlabel">TOTAL QTD.</label>
              <br />
              <label class="bigtitle" style="color: #266fc5">{{ analiseQuantidadeTotal }}</label>
            </template>
          </Card>
        </td>
      </tr>
      <tr>
        <td colspan=5>
          <Card style="width: 52rem; height: 31rem; margin-bottom: 6em; background-color: #F2E0F7; text-align:center" >
            <template #title> Total de vendas por ano </template> 
            <template #content>

              <div style="height: 3rem;" class="card">
                <Chart type="line" :data="lineStylesData" :options="basicOptions" />
              </div>
            </template>
          </Card>
        </td>
      </tr>
    </table>
  </div>

</template>

<script>

import Menu from "@/components/Menu.vue";
import Card from "primevue/card";
import axios from "axios";
import Button from "primevue/button";
import Chart from "primevue/chart";
import DataTable from "primevue/datatable";
import Column from 'primevue/column';
import SelectButton from 'primevue/selectbutton';

export default {
  name: "App",
  components: {
    Menu,
    Card,
    Button,
    Chart,
    DataTable,
    Column,
    SelectButton,
  },
  mounted() {
    this.emailUsuario = this.$route.query.email
    axios.get("http://localhost:8081/item-venda/top").then((response) => {
      this.itens = response.data;
      this.chartData.labels = []
      this.chartData.datasets[0].data = []
      response.data.map(produto => {
        this.chartData.labels.push(produto[0])
        this.chartData.datasets[0].data.push(produto[1])
      })
    })
    axios.get("http://localhost:8081/venda/total").then((response) => {
      this.analiseValorTotal = response.data[0]
      this.analiseQuantidadeTotal = response.data[1]
    })
    axios.get("http://localhost:8081/venda/valor-mes-ano").then((response) => {
      this.lineStylesData.datasets.labels = []
      this.lineStylesData.datasets = [{}]
      let groupAno = []
      let indicesMes = []
      let i = 0
      response.data.map(venda => {
        if(!indicesMes.includes(venda[2])){
          this.lineStylesData.labels.push(this.nomeMes(venda[2]))
          indicesMes.push(venda[2])
        }
        if(!groupAno.includes(venda[1])){
          this.lineStylesData.datasets.push({
            label: venda[1],
            fill: false,
            data: [],
            tension: .4,
            borderColor: this.gerarCor(i)})
          groupAno.push(venda[1])
          i++
        }
        this.lineStylesData.datasets[i].data.push(venda[0])
      })
      this.lineStylesData.datasets.splice(0, 1)
    })
  },
  methods: {
    nomeMes: function(mes){
      switch(mes) {
        case 1: return 'Janeiro'
        case 2: return 'Fevereiro'
        case 3: return 'Março'
        case 4: return 'Abril'
        case 5: return 'Maio'
        case 6: return 'Junho'
        case 7: return 'Julho'
        case 8: return 'Agosto'
        case 9: return 'Setembro'
        case 10: return 'Outubro'
        case 11: return 'Novembro'
        case 12: return 'Dezembro'
        default: return ''
      }
    },
    gerarCor: function(i){
      switch(i){
        case 1: return "#660066"
        case 2: return "#0052cc"
        case 3: return "#e6005c"
        default: return '#000'
      }
    },
    selectChange: function(){
      if(this.value1 == "Cidade"){
        this.dataBarChart = this.basicData1;
        this.optionsBarChart = this.basicOptions1;
      }
      else{
        this.dataBarChart = this.basicData2;
        this.optionsBarChart = this.basicOptions2;
      }
    }
  },
  data() {
    return {
      displayModal: false,
      itens: [],
      produtoExibido: {},
      clientesSexo: [],
      analiseValorTotal: 0,
      analiseQuantidadeTotal: 0,
      emailUsuario: '',
      //Select
            value1: 'Idade',
            options: ['Idade', 'Cidade'],
            justifyOptions: [
                {icon: 'pi pi-align-left', value: 'left'},
            ],

      //Grafico Pizza
      chartData: {
        labels: [], //busca dos produtos mais vendidos
        datasets: [
          {
            data: [],
            backgroundColor: ["#32366F", "#2F5F98", "#2D8BBA", "#41B8D5", "#6CE5E8"]
          },
        ],
      },
      lightOptions: {
        plugins: {
          legend: {
            labels: {
              color: "#495057",
            },
          },
        },
      },
      lightOptions: {
        plugins: {
          legend: {
            labels: {
              color: "#495057",
            },
          },
        },
      },

      //Grafico Linhas
      lineStylesData: {
        labels: [],
        datasets: [{}]
      },
      basicOptions: {
        plugins: {
          legend: {
            labels: {
              color: '#000000'
            }
          }
        },
        scales: {
          x: {
            ticks: {
              color: '#000000'
            },
            grid: {
              color: '#ffffff'
            }
          },
          y: {
            ticks: {
              color: '#000000'
            },
            grid: {
              color: '#55aef6'
            }
          }
        }
      },
      multiAxisOptions: {
        stacked: false,
        plugins: {
          legend: {
            labels: {
              color: '#495057'
            }
          }
        },
        scales: {
          x: {
            ticks: {
              color: '#495057'
            },
            grid: {
              color: '#ebedef'
            }
          },
          y: {
            type: 'linear',
            display: true,
            position: 'left',
            ticks: {
              color: '#495057'
            },
            grid: {
              color: '#ebedef'
            }
          },
          y1: {
            type: 'linear',
            display: true,
            position: 'right',
            ticks: {
              color: '#000000'
            },
            grid: {
              drawOnChartArea: false,
              color: '#000000'
            }
          }
        }
      },

      //Grafico Barras Cidade
      basicData1: {
        labels: ['Aparecida', 'Caçapava', 'Cruzeiro', 'Guaratinguetá', 'Jacareí', 'SJCampos', 'Taubaté'],
        datasets: [
          {
            label: 'Feminino',
            backgroundColor: '#660066',
            data: [65, 59, 80, 81, 56, 55, 40]
          },
          {
            label: 'Masculino',
            backgroundColor: '#e6005c',
            data: [28, 48, 40, 19, 86, 27, 90]
          },
        ]
      },
      multiAxisData1: {
        labels: ['Aparecida', 'Caçapava', 'Cruzeiro', 'Guaratinguetá', 'Jacareí', 'SJCampos', 'Taubaté'],
        datasets: [{
          label: 'Dataset 1',
          backgroundColor: ['#EC407A', '#AB47BC', '#42A5F5', '#7E57C2', '#66BB6A', '#FFCA28', '#26A69A'],
          yAxisID: 'y-axis-1',
          data: [65, 59, 80, 81, 56, 55, 10]
        }, {
          label: 'Dataset 2',
          backgroundColor: '#78909C',
          yAxisID: 'y-axis-2',
          data: [28, 48, 40, 19, 86, 27, 90]
        }]
      },
      stackedData1: {
        labels: ['Aparecida', 'Caçapava', 'Cruzeiro', 'Guaratinguetá', 'Jacareí', 'SJCampos', 'Taubaté'],
        datasets: [{
          type: 'bar',
          label: 'Dataset 1',
          backgroundColor: '#42A5F5',
          data: [50, 25, 12, 48, 90, 76, 42]
        }, {
          type: 'bar',
          label: 'Dataset 2',
          backgroundColor: '#66BB6A',
          data: [21, 84, 24, 75, 37, 65, 34]
        }, {
          type: 'bar',
          label: 'Dataset 3',
          backgroundColor: '#FFA726',
          data: [41, 52, 24, 74, 23, 21, 32]
        }]
      },
      basicOptions1: {
        plugins: {
          legend: {
            labels: {
              color: '#000000'
            }
          }
        },
        scales: {
          x: {
            ticks: {
              color: '#000000'
            },
            grid: {
              color: '#ffffff'
            }
          },
          y: {
            ticks: {
              color: '#000000'
            },
            grid: {
              color: '#55aef6'
            }
          }
        }
      },
      horizontalOptions1: {
        indexAxis: 'y',
        plugins: {
          legend: {
            labels: {
              color: '#495057'
            }
          }
        },
        scales: {
          x: {
            ticks: {
              color: '#495057'
            },
            grid: {
              color: '#ebedef'
            }
          },
          y: {
            ticks: {
              color: '#495057'
            },
            grid: {
              color: '#ebedef'
            }
          }
        }
      },
      multiAxisOptions1: {
        plugins: {
          legend: {
            labels: {
              color: '#495057'
            }
          },
          tooltips: {
            mode: 'index',
            intersect: true
          }
        },
        scales: {
          x: {
            ticks: {
              color: '#495057'
            },
            grid: {
              color: '#ebedef'
            }
          },
          y: {
            type: 'linear',
            display: true,
            position: 'left',
            ticks: {
              min: 0,
              max: 100,
              color: '#495057'
            },
            grid: {
              color: '#ebedef'
            }
          },
          y1: {
            type: 'linear',
            display: true,
            position: 'right',
            grid: {
              drawOnChartArea: false,
              color: '#000000'
            },
            ticks: {
              min: 0,
              max: 100,
              color: '#000000'
            }
          }
        }
      },
      stackedOptions1: {
        plugins: {
          tooltips: {
            mode: 'index',
            intersect: false
          },
          legend: {
            labels: {
              color: '#495057'
            }
          }
        },
        scales: {
          x: {
            stacked: true,
            ticks: {
              color: '#495057'
            },
            grid: {
              color: '#ebedef'
            }
          },
          y: {
            stacked: true,
            ticks: {
              color: '#495057'
            },
            grid: {
              color: '#ebedef'
            }
          }
        }
      },
      //Grafico Barras Idade
      basicData2: {
        labels: ['18| - 24', '24| - 30', '30| - 36', '36| - 42', '42| - 48', '48| - 54', '54| - 60', '60| - 100'],
        datasets: [
          {
            label: 'Feminino',
            backgroundColor: '#660066',
            data: [65, 59, 80, 81, 56, 55, 40]
          },
          {
            label: 'Masculino',
            backgroundColor: '#e6005c',
            data: [28, 48, 40, 19, 86, 27, 90]
          },
        ]
      },
      multiAxisData2: {
        labels: ['18| - 24', '24| - 30', '30| - 36', '36| - 42', '42| - 48', '48| - 54', '54| - 60', '60| - 100'],
        datasets: [{
          label: 'Dataset 1',
          backgroundColor: ['#EC407A', '#AB47BC', '#42A5F5', '#7E57C2', '#66BB6A', '#FFCA28', '#26A69A'],
          yAxisID: 'y-axis-1',
          data: [65, 59, 80, 81, 56, 55, 10]
        }, {
          label: 'Dataset 2',
          backgroundColor: '#78909C',
          yAxisID: 'y-axis-2',
          data: [28, 48, 40, 19, 86, 27, 90]
        }]
      },
      stackedData2: {
        labels: ['18| - 24', '24| - 30', '30| - 36', '36| - 42', '42| - 48', '48| - 54', '54| - 60', '60| - 100'],
        datasets: [{
          type: 'bar',
          label: 'Dataset 1',
          backgroundColor: '#42A5F5',
          data: [50, 25, 12, 48, 90, 76, 42]
        }, {
          type: 'bar',
          label: 'Dataset 2',
          backgroundColor: '#66BB6A',
          data: [21, 84, 24, 75, 37, 65, 34]
        }, {
          type: 'bar',
          label: 'Dataset 3',
          backgroundColor: '#FFA726',
          data: [41, 52, 24, 74, 23, 21, 32]
        }]
      },
      basicOptions2: {
        plugins: {
          legend: {
            labels: {
              color: '#000000'
            }
          }
        },
        scales: {
          x: {
            ticks: {
              color: '#000000'
            },
            grid: {
              color: '#ffffff'
            }
          },
          y: {
            ticks: {
              color: '#000000'
            },
            grid: {
              color: '#55aef6'
            }
          }
        }
      },
      horizontalOptions2: {
        indexAxis: 'y',
        plugins: {
          legend: {
            labels: {
              color: '#495057'
            }
          }
        },
        scales: {
          x: {
            ticks: {
              color: '#495057'
            },
            grid: {
              color: '#ebedef'
            }
          },
          y: {
            ticks: {
              color: '#495057'
            },
            grid: {
              color: '#ebedef'
            }
          }
        }
      },
      multiAxisOptions2: {
        plugins: {
          legend: {
            labels: {
              color: '#495057'
            }
          },
          tooltips: {
            mode: 'index',
            intersect: true
          }
        },
        scales: {
          x: {
            ticks: {
              color: '#495057'
            },
            grid: {
              color: '#ebedef'
            }
          },
          y: {
            type: 'linear',
            display: true,
            position: 'left',
            ticks: {
              min: 0,
              max: 100,
              color: '#495057'
            },
            grid: {
              color: '#ebedef'
            }
          },
          y1: {
            type: 'linear',
            display: true,
            position: 'right',
            grid: {
              drawOnChartArea: false,
              color: '#000000'
            },
            ticks: {
              min: 0,
              max: 100,
              color: '#000000'
            }
          }
        }
      },
      stackedOptions2: {
        plugins: {
          tooltips: {
            mode: 'index',
            intersect: false
          },
          legend: {
            labels: {
              color: '#495057'
            }
          }
        },
        scales: {
          x: {
            stacked: true,
            ticks: {
              color: '#495057'
            },
            grid: {
              color: '#ebedef'
            }
          },
          y: {
            stacked: true,
            ticks: {
              color: '#495057'
            },
            grid: {
              color: '#ebedef'
            }
          }
        }
      }
    }
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

.customlabel {
  font-size: 12pt;
  color: black;
}

.bigtitle {
  font-size: 40pt;
  color: #965096;
  font-weight: bold;
}

.box {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  text-align: center;
  justify-content: center;
}

.selectButton{
  display: flex;
  text-align: center;
}

</style>