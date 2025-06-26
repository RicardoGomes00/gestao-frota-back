estrutura do banco de dados

CREATE TABLE Status_Veiculos (
    id SMALLINT PRIMARY KEY,
    descricao VARCHAR(50) UNIQUE NOT NULL
);


CREATE TABLE Status_Viagens (
    id SMALLINT PRIMARY KEY,
    descricao VARCHAR(50) UNIQUE NOT NULL
);


INSERT INTO Status_Veiculos (id, descricao) VALUES
(1, 'Disponível'),
(2, 'Em Uso'),
(3, 'Inativo'),
(4, 'Em Manutenção');

INSERT INTO Status_Viagens (id, descricao) VALUES
(1, 'AGENDADO'),
(2, 'EM_USO'),
(3, 'FINALIZADO'),
(4, 'CANCELADO');
	

CREATE TABLE Usuarios (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    tipo_perfil VARCHAR(20) NOT NULL CHECK (tipo_perfil IN ('admin', 'motorista')),
    data_criacao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Motoristas (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER UNIQUE NOT NULL REFERENCES Usuarios(id) ON DELETE CASCADE,
    nome_completo VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    cnh_numero VARCHAR(20) UNIQUE NOT NULL,
    cnh_validade DATE NOT NULL,
    telefone VARCHAR(20),
    cep VARCHAR(9) NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    uf VARCHAR(2) NOT NULL
);

-- Tabela de Veículos 
CREATE TABLE Veiculos (
    id SERIAL PRIMARY KEY,
    placa VARCHAR(10) UNIQUE NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    ano INTEGER NOT NULL,
    quilometragem_atual INTEGER NOT NULL DEFAULT 0,
    -- A coluna de status agora é uma chave estrangeira
    status_id SMALLINT NOT NULL REFERENCES Status_Veiculos(id)
);

-- Tabela de Viagens 
CREATE TABLE Viagens (
    id SERIAL PRIMARY KEY,
    veiculo_id INTEGER NOT NULL REFERENCES Veiculos(id),
    motorista_id INTEGER NOT NULL REFERENCES Motoristas(id),
    destino VARCHAR(300) NOT NULL,
    justificativa TEXT,
    data_saida_agendada TIMESTAMP WITH TIME ZONE NOT NULL,
    data_retorno_prevista TIMESTAMP WITH TIME ZONE,
    
    data_saida_efetiva TIMESTAMP WITH TIME ZONE,
    data_retorno_efetiva TIMESTAMP WITH TIME ZONE,
    quilometragem_inicial INTEGER,
    quilometragem_final INTEGER,
    
    status_id SMALLINT NOT NULL REFERENCES Status_Viagens(id)
);


CREATE TABLE Abastecimentos (
    id SERIAL PRIMARY KEY,
    veiculo_id INTEGER NOT NULL REFERENCES Veiculos(id),
    motorista_id INTEGER NOT NULL REFERENCES Motoristas(id),
    viagem_id INTEGER REFERENCES Viagens(id),
    data_abastecimento TIMESTAMP WITH TIME ZONE NOT NULL,
    tipo_combustivel VARCHAR(50) NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    litros DECIMAL(10, 2),
    quilometragem_no_abastecimento INTEGER NOT NULL
);

CREATE TABLE Manutencoes (
    id SERIAL PRIMARY KEY,
    veiculo_id INTEGER NOT NULL REFERENCES Veiculos(id),
    data_inicio DATE NOT NULL,
    data_fim DATE,
    descricao_servico TEXT NOT NULL,
    custo DECIMAL(10, 2),
    oficina VARCHAR(255),
    quilometragem INTEGER
);

CREATE TABLE Ocorrencias (
    id SERIAL PRIMARY KEY,
    viagem_id INTEGER NOT NULL REFERENCES Viagens(id),
    motorista_id INTEGER NOT NULL REFERENCES Motoristas(id),
    data_ocorrencia TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL
);