<template>
<div style="width:100">
  <div style="width:50%">
    <Menu></Menu>
  </div>
  <div style="width:50%">
    <div style="text-align: center; font-size: 40pt; text-shadow: 1px 1px 3px black">
      Hey Alexia
    </div>
  
  <div class="row" style="margin-left:5%; margin-top:2%; display: flex;">
  <div>
          <Card style="width: 25rem; height:30rem; margin-bottom: 6em; margin-top: 0%;  background-color: #F2E0F7">
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
          <Card style="width: 25rem; height:20rem; margin-bottom: 10em; margin-top: 0%; background-color: #F2E0F7">
            <template #title> Clientes por idade </template>
            <template #content>
              <div>
                  <div class="card">
                    <Chart type="bar" :data="basicData1" :options="basicOptions1" />

                  </div>
              </div>
            </template>
            </Card>
          <Card style="width: 25rem; height: 28rem; background-color: #F2E0F7">
            <template #title> Produtos mais vendidos (%) </template>
            <template #content>
              <div class="container">
                <Chart type="pie" :data="chartData" :options="lightOptions" />
              </div>
            </template>
          </Card>
  </div>
  <div>
 <Card style="width: 25rem; height:28rem; background-color: #F2E0F7;" class="box">
            <template #title> Análise Mensal </template>
            <template #content>
              <br/><br/><br/>
              <label class="customlabel">TOTAL R$</label>
              <br/>
              <label class="bigtitle">124.5201,87</label>
              <hr/>
              <label class="customlabel">TOTAL QTD.</label>
              <br/>
              <label class="bigtitle" style="color: #266fc5">50</label>
            </template>
          </Card>
          <Card style="width: 52rem; height: 31rem; margin-bottom: 6em; background-color: #F2E0F7">
           <!-- <Card style="width: 32rem; height: 25rem; margin-bottom: 6em; margin-left: 5%; background-color: #F2E0F7"> -->
            <template #title> Total de vendas por ano </template>
            <template #content>
              <!-- <div class="container">
                <div class="card">
                  <h5>Basic</h5>
                    <Chart type="line" :data="basicData" :options="basicOptions" />
              </div> -->
              <!-- <div class="card">
                <h5>Multi Axis</h5>
                <Chart type="line" :data="multiAxisData" :options="multiAxisOptions" />
              </div> -->

               <div style="height: 3rem;" class="card">
                  <Chart type="line" :data="lineStylesData" :options="basicOptions" />
              </div>
              <!-- </div> -->
            </template>
          </Card>
  </div>

         
  </div>
  </div>
</div>
</template>

<script>
import Menu from "./components/Menu.vue";
import Card from "primevue/card";
import axios from "axios";
import Button from "primevue/button";
import Chart from "primevue/chart";
import DataTable from "primevue/datatable";
import Column from 'primevue/column';
import Divider from 'primevue/divider';
import { ref } from 'vue';

export default {
  name: "App",
  components: {
    Menu,
    Card,
    Button,
    Chart,
    DataTable,
    Column,
  },
  mounted() {
    axios.get("http://localhost:8081/item-venda/top").then((response) => {
      this.itens = response.data;
      this.chartData.labels = []
      this.chartData.datasets[0].data = []
      response.data.map(produto => {
        this.chartData.labels.push(produto[0])
        this.chartData.datasets[0].data.push(produto[1])
      })
    })
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
      clientesSexo: [],
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
      basicData: {
          labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
          datasets: [
           {
             label: 'First Dataset',
              data: [65, 59, 80, 81, 56, 55, 40],
              fill: false,
              borderColor: '#800080',
              tension: .4
           },
           {
             label: 'Second Dataset',
             data: [28, 48, 40, 19, 86, 27, 90],
             fill: false,
             borderColor:  '#0099ff',
             tension: .4
           }
          ]
        }, 
        multiAxisData: {
          labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
          datasets: [{
            label: 'Dataset 1',
                    fill: false,
                     borderColor: '#000000',
                    yAxisID: 'y',
                     tension: .4,
                     data: [65, 59, 80, 81, 56, 55, 10]
                }, {
                    label: 'Dataset 2',
                    fill: false,
                    borderColor: '#000000',
                     yAxisID: 'y1',
                    tension: .4,
                     data: [28, 48, 40, 19, 86, 27, 90]
                 }]
             }, 
            lineStylesData: {
                 labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
                 datasets: [
                    {
                         label: 'First Dataset',
                         data: [65, 59, 80, 81, 56, 55, 40],
                         fill: false,
                         tension: .4,
                         borderColor: '#660066' //roxo
                     },
                     {
                         label: 'Second Dataset',
                         data: [28, 48, 40, 19, 86, 27, 90],
                         fill: false,
                         borderDash: [5, 5],
                         tension: .4,
                         borderColor:  '#0052cc'
                     },
                     {
                         label: 'Third Dataset',
                         data: [12, 51, 62, 33, 21, 62, 45],
                         fill: true,
                         borderColor: '#e6005c', //rosa
                         tension: .4,
                         backgroundColor: 'rgba(255,167,38,0.2)'
                     }
                 ]
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
                            color:  '#55aef6'
                        }
                    }
                }
            },
              multiAxisOptions:{
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
                    {
                        label: 'Outros',
                        backgroundColor: '#3396ff',
                        data: [28, 48, 40, 19, 86, 27, 90]
                    }
                ]
            },
            multiAxisData1: {
                labels: ['Aparecida', 'Caçapava', 'Cruzeiro', 'Guaratinguetá', 'Jacareí', 'SJCampos', 'Taubaté'],
                datasets: [{
                    label: 'Dataset 1',
                    backgroundColor: ['#EC407A','#AB47BC','#42A5F5','#7E57C2','#66BB6A','#FFCA28','#26A69A'],
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
                    data: [50,25,12,48,90,76,42]
                }, {
                    type: 'bar',
                    label: 'Dataset 2',
                    backgroundColor: '#66BB6A',
                    data: [21,84,24,75,37,65,34]
                }, {
                    type: 'bar',
                    label: 'Dataset 3',
                    backgroundColor: '#FFA726',
                    data: [41,52,24,74,23,21,32]
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
                            color: '#495057'//'#495057'
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

* { 
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    width:100%;
}

</style>