# bluetech-backend

web API desenvolvida em Java, com spring boot framework. 
segurança com spring security, jjw, testes unitários com JUnit e banco de dados MySql

# documentação da API

base URL = 'http://ip:8080',

Usuário:

	inserir
	http://baseUrl/usuario/cadastrar-usuario/    metodo = POST requestBody
	{
		nome, email, senha
	}

	listar todos
	http://baseUrl/usuario/listar-todos-usuarios/ metodo = GET
	retorno - [ {id, nome, email}, ..., {...} ]

	listar por id
	http://baseUrl/usuario/listar-por-id/id       metodo = GET
	retorno - {id, nome, email}

Login:

	http://baseUrl/auth/     metodo = POST requestBody
	{
		email, senha
	}
	retorno - {token, id, nome, email}

Imovel:

	inserir
	http://baseUrl/imovel/cadastra-imovel/  metodo = POST requestBody
	{
		nomeImovel, imagem:[{imagemPath}, ... , {imagemPath}]
	}

	listar todos
	http://baseUrl/imovel/listar-imoveis/  metodo = GET
	retorno - [{id, nomeImovel, imagem:[{imagemPath}, ...  ]}, ..., {...}]
	
	listar por id
	http://baseUrl/imovel/listar-imovel-id/id    metodo = GET
	retorno - {id, nomeImovel, imagem:[{imagemPath}, ...  ]}

Votação:

	inserir voto  -  necessário tem token válido para conseguir acessar a url
	Header - "Authorizarion" : "Bearer "+token 
	http://baseUrl/imovel/inserir-voto/   metodo = POST requestBody
	{
		idUsuario, idImovel	
	}

	listar dados imovel especifico
	http://baseUrl/imovel/dados-votacao-imovel/id   metodo = GET
	retorno - {nomeImovel, totalVotosImovel, nomeUsuario,}

	listar todos os dados de votacao
	http://baseUrl/imovel/dados-geral-votos/    metodo = GET
	retorno - [ {nomeImovel, totalVotosImovel, nomeUsuario, totalVotos}, ..., {...} ]

# Como executar

Download do projeto
Abrir spring boot -> import -> maven project -> configurar o banco de dados mysql no application.properties (url, user, password)-> run as-> spring boot App 

Recomendado utilizar postman para simular o consumo e cadastrar os imóveis com as sequintes URL's respectivas
