USE [master]
GO
/****** Object:  Database [SE1403]    Script Date: 3/24/2020 6:11:45 PM ******/
CREATE DATABASE [SE1403]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SE1403', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\SE1403.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'SE1403_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\SE1403_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [SE1403] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SE1403].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SE1403] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SE1403] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SE1403] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SE1403] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SE1403] SET ARITHABORT OFF 
GO
ALTER DATABASE [SE1403] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SE1403] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SE1403] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SE1403] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SE1403] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SE1403] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SE1403] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SE1403] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SE1403] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SE1403] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SE1403] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SE1403] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SE1403] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SE1403] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SE1403] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SE1403] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SE1403] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SE1403] SET RECOVERY FULL 
GO
ALTER DATABASE [SE1403] SET  MULTI_USER 
GO
ALTER DATABASE [SE1403] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SE1403] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SE1403] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SE1403] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [SE1403] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'SE1403', N'ON'
GO
USE [SE1403]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 3/24/2020 6:11:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[OrderID] [int] NOT NULL,
	[ProductName] [nvarchar](50) NOT NULL,
	[Quantity] [int] NOT NULL,
 CONSTRAINT [PK_OrderDetail] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC,
	[ProductName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Orders]    Script Date: 3/24/2020 6:11:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderID] [int] NOT NULL,
	[Total] [int] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Products]    Script Date: 3/24/2020 6:11:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[ProductID] [int] NOT NULL,
	[Tittle] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[Tittle] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Registration]    Script Date: 3/24/2020 6:11:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Registration](
	[username] [varchar](20) NOT NULL,
	[password] [varchar](30) NOT NULL,
	[fullname] [nvarchar](250) NOT NULL,
	[isAdmin] [bit] NOT NULL,
 CONSTRAINT [PK_Registration] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[OrderDetail] ([OrderID], [ProductName], [Quantity]) VALUES (1, N'Java', 3)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductName], [Quantity]) VALUES (1, N'NetBeans', 1)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductName], [Quantity]) VALUES (1, N'Servlet', 1)
INSERT [dbo].[Orders] ([OrderID], [Total]) VALUES (1, 3)
INSERT [dbo].[Products] ([ProductID], [Tittle]) VALUES (1, N'Java')
INSERT [dbo].[Products] ([ProductID], [Tittle]) VALUES (7, N'JavaBeans')
INSERT [dbo].[Products] ([ProductID], [Tittle]) VALUES (2, N'JDK')
INSERT [dbo].[Products] ([ProductID], [Tittle]) VALUES (6, N'JSP')
INSERT [dbo].[Products] ([ProductID], [Tittle]) VALUES (8, N'JSTL')
INSERT [dbo].[Products] ([ProductID], [Tittle]) VALUES (3, N'NetBeans')
INSERT [dbo].[Products] ([ProductID], [Tittle]) VALUES (9, N'Others')
INSERT [dbo].[Products] ([ProductID], [Tittle]) VALUES (5, N'Servlet')
INSERT [dbo].[Products] ([ProductID], [Tittle]) VALUES (4, N'Tomcat')
INSERT [dbo].[Registration] ([username], [password], [fullname], [isAdmin]) VALUES (N'nguyen123', N'12345678', N'dong nguyen ', 1)
INSERT [dbo].[Registration] ([username], [password], [fullname], [isAdmin]) VALUES (N'user', N'123456', N'hoangphuc', 0)
INSERT [dbo].[Registration] ([username], [password], [fullname], [isAdmin]) VALUES (N'user12345', N'123456', N'123456', 0)
INSERT [dbo].[Registration] ([username], [password], [fullname], [isAdmin]) VALUES (N'user123456', N'123456', N'user123', 0)
INSERT [dbo].[Registration] ([username], [password], [fullname], [isAdmin]) VALUES (N'user12346', N'phucprovip952', N'haongp2222', 0)
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Orders] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Orders] ([OrderID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Orders]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Products] FOREIGN KEY([ProductName])
REFERENCES [dbo].[Products] ([Tittle])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Products]
GO
USE [master]
GO
ALTER DATABASE [SE1403] SET  READ_WRITE 
GO
