## Requisito 6 individual: Mônica de Souza Schneider

Nesse requisito optei por implementar a avaliação de prejuízo causado pela quantidade de produtos vencidos parados em estoque.

[Requisito 6](https://docs.google.com/document/d/1FWNm0zGHImoh-YvxqscE2r6UQKhIih1e/edit?usp=sharing&ouid=104577740136334641024&rtpof=true&sd=true) 

[Collection-Postman](https://www.getpostman.com/collections/3e29628f42dc6dc8d81c)

``` 

```
### <center> 💡 Features 💡</center>
- Novo setor "Vencidos": Adionado setor específico para armazenar todos os produtos vencidos parados em estoque
- Transferir de setor: Transferidos todos os produtos vencidos de seus respectivos setores originais para o "Vencidos"
- Prejuízo mensal: Dado determinado mês, calculado o prejuízo causado pelos produtos vencidos parados em estoque, em relação ao total de produtos, em porcentagem
- Prejuízo anual: Predição de prejuízo anual baseada em regressão linear como aproximação de todos os prejuízos mensais durante o ano.

###<center> Model </center>
#### <center> O modelo abaixo é baseado na aplicação por completo. Não foi adicionada uma nova entidade para oo presente requisito <center>
![alt text](https://cdn.discordapp.com/attachments/994271189616840765/1009214070374797312/modelDesafio3.png)

### <center> 🛣️ Routes 🛣️</center>
 
-  📪 Post: insertSector()
-  ➡️ Put: transferToSector()
-  ⬅️ Get: expiredQuantitySector (String month)
-  ⬅️ Get: verifyRegression ()

|  HTTP  | URL MODEL                                | DESCRIPTION                                                                     |    US-CODE     |
|:------:|:-----------------------------------------|:--------------------------------------------------------------------------------|:--------------:|
| `POST` | /api/v1/fresh-products/sector/insert     | Registra setor "Vencidos"        | ml-e-wallet-06 |
| `PUT`  | /fresh-products/sectorProducts/dueDate   | Transfere todos os produtos vencidos para o setor criado          | ml-e-wallet-06 |
| `GET`  | /api/v1/fresh-products/sectorProducts/expired/{month}    | Retorna o prejuízo do mês        | ml-e-wallet-06 |
| `GET`  | /api/v1/fresh-products/sectorProducts/finance | Retorna a predição do prejuízo anual                                            | ml-e-wallet-06 |



### <center>⚗️ Cobertura de testes ⚗️ </center>
<center> - 66% of classes </center>

### <center> Regressão linear </center>
Linha de regressão a ser obtida: y=a*x + b

onde 

###<center> ![alt text](https://cdn.discordapp.com/attachments/994271189616840765/1009217106258182154/Captura_de_Tela_2022-08-16_as_18.43.21.png) <center>

#### <center> Equação da reta obtida a partir dos valores de prejuízo durante os meses de 2021: #### 


##<center> ![alt text](https://cdn.discordapp.com/attachments/994271189616840765/1009423976998641744/WhatsApp_Image_2022-08-17_at_08.13.35.jpeg) <center>
