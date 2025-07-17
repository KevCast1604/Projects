using ejemplo_eb.Manufacturing.Domain.Model.Aggregates;
using ejemplo_eb.Manufacturing.Domain.Model.Commands;
using ejemplo_eb.Manufacturing.Domain.Repository;
using ejemplo_eb.Manufacturing.Domain.Services;
using ejemplo_eb.Manufacturing.Interfaces.ACL;
using System;
using System.Threading.Tasks;

namespace ejemplo_eb.Manufacturing.Application.Internal.CommandServices
{
    public class CreateBillOfMaterialsItemCommandService : IBillOfMaterialsItemCommandService
    {
        // Declara los campos privados aquí dentro de la clase
        private readonly IBillOfMaterialsItemRepository _repository;
        private readonly IInventoryAclService _inventoryAclService;

        // Constructor
        public CreateBillOfMaterialsItemCommandService(
            IBillOfMaterialsItemRepository repository,
            IInventoryAclService inventoryAclService)
        {
            _repository = repository;
            _inventoryAclService = inventoryAclService;
        }

        // Método público de la clase
        public async Task<BillOfMaterialsItem> CreateAsync(CreateBillOfMaterialsItemCommand command)
        {
            // Validar existencia del producto a través del ACL
            bool productExists = await _inventoryAclService.ProductExistsAsync(command.ItemProductNumber.ToString());
            if (!productExists)
                throw new InvalidOperationException("Product does not exist in Inventory.");

            // Validar fechas
            if (command.RequiredAt > DateTime.UtcNow)
                throw new ArgumentException("RequiredAt cannot be in the future.");

            if ((command.ScheduledStartAt - command.RequiredAt).TotalDays < 30)
                throw new ArgumentException("ScheduledStartAt must be at least 30 days after RequiredAt.");

            // Validar combinación única
            bool existsComposite = await _repository.ExistsByCompositeKeyAsync(
                command.ItemProductNumber,
                command.BatchId,
                command.BillOfMaterialsId
            );

            if (existsComposite)
                throw new InvalidOperationException("BillOfMaterialsItem with this composite key already exists.");

            if (command.BillOfMaterialsId <= 0)
                throw new ArgumentException("BillOfMaterialsId must be greater than zero.");

            if (command.BatchId <= 0)
                throw new ArgumentException("BatchId must be greater than zero.");

            if (command.RequiredQuantity <= 0)
                throw new ArgumentException("RequiredQuantity must be greater than zero.");


            // Crear la entidad aggregate root
            var item = new BillOfMaterialsItem(
                command.BillOfMaterialsId,
                command.ItemProductNumber,
                command.BatchId,
                command.RequiredQuantity,
                command.ScheduledStartAt,
                command.RequiredAt
            );

            await _repository.AddAsync(item);

            return item;
        }
    }
}
