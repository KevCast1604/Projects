using ejemplo_eb.Inventories.Domain.Model.Commands;
using EjemploEb.Inventories.Domain.Model.Aggregates;

namespace ejemplo_eb.Inventories.Domain.Services
{
    public interface IInventoryCommandService
    {
        Task<Product> CreateProductAsync(CreateProductCommand command);
        // Otros comandos futuros (UpdateProductAsync, DeleteProductAsync, etc)
    }
}
