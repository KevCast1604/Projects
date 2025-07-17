using ejemplo_eb.Manufacturing.Domain.Model.Aggregates;
using ejemplo_eb.Manufacturing.Interfaces.REST.Resources;

namespace ejemplo_eb.Manufacturing.Interfaces.REST.Transforms
{
    public static class BillOfMaterialItemResourceFromEntityAssembler
    {
        public static BillOfMaterialsItemResource ToResource(this BillOfMaterialsItem item)
        {
            return new BillOfMaterialsItemResource(
                item.Id,
                item.BillOfMaterialsId,
                item.ItemProductNumber.ToString(),
                item.BatchId,
                item.RequiredQuantity,
                item.ScheduledStartAt,
                item.RequiredAt,
                item.CreatedAt,
                item.UpdatedAt
            );
        }
    }
}