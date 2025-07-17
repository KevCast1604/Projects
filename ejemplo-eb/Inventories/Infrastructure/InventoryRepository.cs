using ejemplo_eb.Inventories.Domain.Repository;
using ejemplo_eb.Shared.Infrastructure.Persistence.EFC.Configuration;
using EjemploEb.Inventories.Domain.Model.Aggregates;
using Microsoft.EntityFrameworkCore;

namespace ejemplo_eb.Inventories.Infrastructure
{
    public class InventoryRepository : IInventoryRepository
    {
        private readonly AppDbContext _context;

        public InventoryRepository(AppDbContext context)
        {
            _context = context;
        }

        public async Task AddAsync(Product product)
        {
            await _context.Products.AddAsync(product);
            await _context.SaveChangesAsync();
        }

        public async Task<Product?> FindByIdAsync(int id)
        {
            return await _context.Products.FirstOrDefaultAsync(p => p.Id == id);
        }

        public async Task<bool> ExistsByNameAsync(string name)
        {
            return await _context.Products.AnyAsync(p => p.Name == name);
        }

        public async Task<bool> ExistsByProductNumberAsync(string productNumber)
        {
            if (!Guid.TryParse(productNumber, out var parsedGuid))
                return false; // O lanza una excepción si prefieres

            return await _context.Products.AnyAsync(p => p.ProductNumber == parsedGuid);
        }

    }
}
