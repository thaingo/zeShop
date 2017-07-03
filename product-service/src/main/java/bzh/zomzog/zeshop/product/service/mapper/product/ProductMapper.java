package bzh.zomzog.zeshop.product.service.mapper.product;

import bzh.zomzog.zeshop.product.domain.Product;
import bzh.zomzog.zeshop.product.service.dto.product.ProductDTO;
import bzh.zomzog.zeshop.product.service.mapper.ImageMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Mapper for the entity Product and its DTO ProductDTO.
 */
@Mapper(componentModel = "spring", uses = {ProductCustomizationFieldMapper.class, ImageMapper.class})
public interface ProductMapper {

    ProductDTO productToProductDTO(Product product);

    List<ProductDTO> productsToProductDTOs(List<Product> products);

    @Mappings({ //
            @Mapping(target = "createdDate", ignore = true), //
            @Mapping(target = "updatedDate", ignore = true), //
    })
    Product productDTOToProduct(ProductDTO productDTO);

    List<Product> productDTOsToProducts(List<ProductDTO> productDTOs);

    @Mappings({ //
            @Mapping(target = "createdDate", ignore = true), //
            @Mapping(target = "updatedDate", ignore = true), //
    })
    Product update(ProductDTO productDTO, @MappingTarget Product product);
}
