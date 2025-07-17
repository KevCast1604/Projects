using ejemplo_eb.Inventories.Domain.Model.Commands;
using ejemplo_eb.Inventories.Domain.Repository;
using ejemplo_eb.Inventories.Domain.Services;
using ejemplo_eb.Shared.Domain.Configuration;
using EjemploEb.Inventories.Domain.Model.Aggregates;
using Microsoft.Extensions.Options;

namespace ejemplo_eb.Inventories.Application.Internal.CommandServices
{
    public class CreateProductCommandService : IInventoryCommandService
    {
        private readonly IInventoryRepository _repository;
        private readonly CapacityThresholdsConfig _capacityConfig;

        public CreateProductCommandService(IInventoryRepository repository, IOptions<CapacityThresholdsConfig> capacityConfig)
        {
            _repository = repository;
            _capacityConfig = capacityConfig.Value;
        }

        public async Task<Product> CreateProductAsync(CreateProductCommand command)
        {
            // 1. Validar campos obligatorios
            if (string.IsNullOrWhiteSpace(command.Name))
                throw new ArgumentException("Name is required");

            if (command.Name.Length > 60)
                throw new ArgumentException("Name must not exceed 60 characters");

            // 2. Validar unicidad de name
            if (await _repository.ExistsByNameAsync(command.Name))
                throw new InvalidOperationException("Product name must be unique");

            // 3. Validar rango de maxProductionCapacity según configuración
            if (command.MaxProductionCapacity < _capacityConfig.MinCapacityThreshold ||
                command.MaxProductionCapacity > _capacityConfig.MaxCapacityThreshold)
                throw new ArgumentException($"MaxProductionCapacity must be between {_capacityConfig.MinCapacityThreshold} and {_capacityConfig.MaxCapacityThreshold}");

            // 4. Validar y mapear productType
            var productType = command.ProductTypeCode switch
            {
                "BTP" => EProductType.BuildToPrint,
                "BTS" => EProductType.BuildToSpecification,
                "MTS" => EProductType.MadeToStock,
                "MTO" => EProductType.MadeToOrder,
                "MTA" => EProductType.MadeToAssemble,
                _ => throw new ArgumentException("Invalid product type code")
            };

            // 5. Crear entidad Product (productNumber se genera internamente)
            var product = new Product(command.Name, productType, command.MaxProductionCapacity);

            // 6. currentProductionQuantity se inicia en 0 (en el constructor)

            // 7. Agregar y guardar en repositorio
            await _repository.AddAsync(product);

            return product;
        }
    }
}
