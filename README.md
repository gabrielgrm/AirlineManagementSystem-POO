# Sistema de Gerenciamento de Companhia Aérea - POO

Este repositório contém as classes Java desenvolvidas para o projeto de atividade continuada da disciplina de Programação Orientada a Objetos (POO). O sistema é projetado para administrar operações de uma companhia aérea, incluindo cadastro de clientes, gestão de voos, emissão e gerenciamento de bilhetes aéreos, tanto regulares quanto VIPs.

## Estrutura do Projeto

O projeto está organizado em vários pacotes, refletindo diferentes aspectos do sistema de gestão de companhia aérea:

- `br.edu.cesarschool.cc.poo.ac.cliente`: Classes relacionadas ao gerenciamento de clientes.
- `br.edu.cesarschool.cc.poo.ac.passagem`: Classes para gerenciamento de voos e bilhetes.
- `br.edu.cesarschool.cc.poo.ac.utils`: Utilitários gerais, incluindo validadores e manipuladores de strings.

### Principais Classes

- `Cliente`: Representa um cliente que compra bilhetes.
- `Voo`: Representa detalhes de um voo.
- `Bilhete`, `BilheteVip`: Representam bilhetes regulares e VIPs respectivamente.
- `ValidadorCPF`, `StringUtils`: Fornece utilitários para validação de CPF e manipulação de strings.

### DAOs

Data Access Object (DAO) classes são usadas para persistência de dados em arquivos:

- `ClienteDAO`
- `VooDAO`
- `BilheteDAO`
- `BilheteVipDAO`

### Utilização

Cada classe está documentada com instruções sobre como as entidades são criadas, manipuladas e validadas. Os DAOs são usados para interagir com os arquivos de dados, seguindo o modelo especificado.

### Como executar

1. Certifique-se de que Java está instalado em sua máquina.
2. Clone o repositório.
3. Navegue até o diretório do projeto e compile os arquivos `.java`.
4. Execute as classes conforme necessário para testar as diferentes funcionalidades.

## Contribuições

Contribuições para o projeto são bem-vindas. Antes de enviar sua contribuição, por favor, revise as diretrizes de contribuição.

## Testes

O sistema inclui testes automatizados usando JUnit para garantir a corretude e eficácia das funcionalidades implementadas. Os testes cobrem desde a validação de dados de entrada até a lógica de negócios das operações principais.

![tests](https://github.com/gabrielgrm/AirlineManagementSystem-POO/assets/131777675/6f56e5ca-b02b-4d8f-83b1-3c9a238ec160)

## Contato

Para mais informações, entre em contato com o administrador do repositório.
