## <center> Requisito 6 individual: Nicole </center>

Nesse requisito optei por implementar a funcionalidade que adiciona reviews e calcula a média do vendedor, depois de falhar miseravelmente em implementar autenticação nas rotas com spring security e JWT. Mas eu falhei miseravelmente em fazer isso também e no fim fiz o crud nosso de cada dia na rota do Seller 🥺👉👈.
<br>
<br>
### <center> 💡 Features 💡</center> 
- Lista todos os vendedores 
- Pesquisa vendedor por id  
- Deleta vendedor por id
<br>
<br>

### <center> 🛣️ Routes 🛣️</center>

|  HTTP  | URL MODEL                                | DESCRIPTION                                                                     |    US-CODE     |
|:------:|:-----------------------------------------|:--------------------------------------------------------------------------------|:--------------:|
| `GET` ⬅️| /api/v1/fresh-products/seller/list/1  | Essa rota tertorna um vendedor por id                                    | ml-seller-06 |
| `GET`  ⬅️ | api/v1/fresh-products/seller/list  | Essa rota retorna todos os vendedores           | ml-seller-06 |
| `DELETE` 🗑️ | api/v1/fresh-products/seller/delete/1  | Essa rota deleta um vendedor por id           | ml-seller-06 |

<br>
<br>

### <center> 👮 Postman 👮 </center>
 A collection de rotas do postmans está [aqui](https://www.getpostman.com/collections/a5527d64f9f157a4e3a6), mas pode ser encontrada dentro dos arquivos do projeto também!

### <center>⚗️ Cobertura de testes ⚗️ </center>
<center> - porcentagem: 0% | Comecei a fazer mas não deu tempo de terminar </center>

 ![alt text](https://i.pinimg.com/originals/d0/1d/7d/d01d7d8fe1f31bf1a870a97490a0e8f3.jpg)
<br>
<br>

### <center> 🐛 Bugs 🐛</center>
- Não lança exception como deveria
<br>
<br>
 