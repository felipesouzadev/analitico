# Exercício Seletivo Desenvolvedor BackEnd - Análise de Dados.
Rotina de análise de dados desenvolvida com o framework Spring Batch.
## Descrição da Rotina
A rotina lê registros de vendas a partir  de um arquivo lote de texto(.txt) que deve estar no diretório do usuário "homepath/data/in".
Após a leitura de todos registros existentes no lote é criado um arquivo texto no diretório "homepath/data/out" com o relatório das vendas além de renomear o arquivo de entrada para ".processado".

## Tipos de Registro e exemplo.
* Vendedor. Ex: 001çCPFçNameçSalary
* Cliente.  Ex: 002çCNPJçNameçBusiness Area
* Venda.    Ex: 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name

## Informações do relatório
* Quantidade de clientes no arquivo de entrada.
* Quantidade de vendedores no arquivo de entrada.
* ID da venda mais cara.
* O pior vendedor.
