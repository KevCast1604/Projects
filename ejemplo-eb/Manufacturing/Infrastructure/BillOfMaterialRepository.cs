using ejemplo_eb.Manufacturing.Domain.Model.Aggregates;
using ejemplo_eb.Manufacturing.Domain.Repository;
using ejemplo_eb.Shared.Infrastructure.Persistence.EFC.Configuration;
using Microsoft.EntityFrameworkCore;

public class BillOfMaterialsItemRepository : IBillOfMaterialsItemRepository
{
    private readonly AppDbContext _context;
    private readonly DbSet<BillOfMaterialsItem> _items;

    public BillOfMaterialsItemRepository(AppDbContext context)
    {
        _context = context;
        _items = context.BillOfMaterialsItems;
    }

    public async Task<BillOfMaterialsItem?> FindByIdAsync(int id)
    {
        return await _items.FirstOrDefaultAsync(b => b.Id == id);
    }

    public async Task AddAsync(BillOfMaterialsItem item)
    {
        await _items.AddAsync(item);
        await _context.SaveChangesAsync();
    }

    public async Task<bool> ExistsByCompositeKeyAsync(Guid itemProductNumber, int batchId, int billOfMaterialsId)
    {
        return await _items.AnyAsync(b =>
            b.ItemProductNumber == itemProductNumber &&
            b.BatchId == batchId &&
            b.BillOfMaterialsId == billOfMaterialsId
        );
    }

    public async Task<List<BillOfMaterialsItem>> ListAsync()
    {
        return await _items.ToListAsync();
    }

    public void Remove(BillOfMaterialsItem item)
    {
        _items.Remove(item);
        // Recuerda llamar SaveChangesAsync luego de llamar Remove
    }
}
