# Spring AI 学习实践（spring-ai-study）
本项目是 Spring AI 的入门学习实践工程，涵盖大模型对接、向量数据库集成、RAG 等核心功能的实现示例，帮助快速掌握 Spring AI 核心用法。

## 🔧 技术栈
| 技术/组件         | 版本/规格                     | 说明                     |
|-------------------|------------------------------|--------------------------|
| Spring Boot       | 3.5.9                        | 基础开发框架             |
| JDK               | 17                           | 运行环境（Spring AI 要求）|
| Spring AI         | 1.1.2                        | AI 应用开发核心框架      |
| Chroma            | 1.0.0                        | 轻量级向量数据库         |
| 大模型            | DeepSeek                     | 对话/生成类大模型        |
| 嵌入模型          | Nomic-embed-text（Ollama 部署） | 文本向量化模型           |
| 本地模型运行环境  | Ollama                       | 本地部署嵌入模型         |

## 🚀 环境搭建
### 1. 大模型（DeepSeek）配置
- 访问 DeepSeek 官方平台申请 API Key：[https://platform.deepseek.com/usage](https://platform.deepseek.com/usage)
- 申请后将 Key 配置到项目的 `application.yml` 中（示例见下方「配置示例」）。

### 2. 嵌入模型（本地 Ollama 部署）
#### 2.1 安装 Ollama
- 下载地址：[https://ollama.com/download](https://ollama.com/download)（根据操作系统选择对应版本）。
- 安装完成后验证：终端执行 `ollama --version`，能输出版本号即安装成功。

#### 2.2 拉取嵌入模型
```bash
# 拉取 nomic-embed-text 嵌入模型（轻量、适合入门）
ollama pull nomic-embed-text

# 验证模型是否拉取成功
ollama list # 输出中应包含 nomic-embed-text
```
#### 2.3 向量数据库（Chroma 1.0.0）
```bash
docker run -it --rm --name chroma -p 8000:8000 ghcr.io/chroma-core/chroma:1.0.0
```

## 📚 示例清单
- 大模型对接（单/多个大模型接入）
- 流式输出
- 日志拦截器
- 提示词工程
- 结构化输出
- 工具调用
- 向量数据库存储
- RAG（检索增强生成）