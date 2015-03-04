#include <WinSock2.h>
#include <Windows.h>
#include <stdio.h>
#include <iostream>
using namespace std;

#pragma comment(lib, "Ws2_32.lib")

SOCKET Socket;
WSADATA Winsock;
sockaddr_in Addr;
int Addrlen = sizeof(Addr);

int main()
{
	 WSAStartup(MAKEWORD(2, 2), &Winsock);    

	if (LOBYTE(Winsock.wVersion) != 2 || HIBYTE(Winsock.wVersion) != 2)    
	{
		WSACleanup();
		return 0;
	}

	Socket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);

	ZeroMemory(&Addr, sizeof(Addr));   
	Addr.sin_family = AF_INET;   
	Addr.sin_addr.s_addr = inet_addr("127.0.0.1");
	Addr.sin_port = htons(8080);   

	if (connect(Socket, (sockaddr*)&Addr, sizeof(Addr)) < 0)
	{
		printf("Connection failed !\n");
		getchar();
		return 0;
	}

	printf("Connected....!!\n");



	int Size;
	char *Filesize = new char[1024];

	if (recv(Socket, Filesize, 1024, 0)) 
	{
		Size = atoi((const char*)Filesize);
		printf("File size: %d\n", Size);
	}

	char *Buffer = new char[Size];


	int Offset = 0;
	while (Size > Offset)
	{
		int Amount = recv(Socket, Buffer + Offset, Size - Offset, 0);

		if (Amount <= 0)
		{
			cout << "Error: " << WSAGetLastError() << endl;
			break;
		}
		else
		{
			Offset += Amount;
			printf("2\n");
		}
	}

	FILE *File;
	File = fopen("Hello.txt", "wb");
	fwrite(Buffer, 1, Size, File);
	fclose(File);

	getchar();
	closesocket(Socket);
	WSACleanup();
	return 0;
}
