package com.healthcare.filestorage.configuration;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.healthcare.filestorage.utilities.AppConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobConfigs {
    @Bean
    BlobContainerClient getBlobContainerClient(){
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .endpoint(AppConstants.AZURE_BLOB_ENDPOINT)
                .sasToken(AppConstants.AZURE_BLOB_SAS_TOKEN)
                .buildClient();

        BlobContainerClient containerClient;
        containerClient = blobServiceClient.getBlobContainerClient(AppConstants.AZURE_BLOB_CONTAINER);
        return containerClient;
    }
}
