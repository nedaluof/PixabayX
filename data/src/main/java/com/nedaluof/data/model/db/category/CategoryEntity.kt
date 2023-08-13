package com.nedaluof.data.model.db.category

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created By NedaluOf - 8/9/2023.
 */
@Entity(tableName = "categories_table")
data class CategoryEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
  var name: String = "",
  var coverImage: String = ""
)

internal fun getPrePopulatedCategories(): List<CategoryEntity> {
  val categoryData = listOf(
    "backgrounds" to "https://pixabay.com/get/g79fb12e7369d426c9dc790d85660e076ce01c7ce9a3d90fa5eab63a5f1f32b525a27e36608e4ce04020aafb2c96a10679a1cb4cd05859e8e4ddee9a9866ed058_1280.jpg",
    "fashion" to "https://pixabay.com/get/g3760fa80880bcc2b3b84af502fa068e469975047443aeefc60138da1dbc4c384f175aa97f48a227d5ae9d74f807f3d104901dedf7c3f830099d037c3e8e2ccd0_1280.jpg",
    "nature" to "https://pixabay.com/get/gad96b74b7e0d2922fb3755239f4b006ffabde044c86cdf796638c388e966013079e5c5d2d984eaf6f0c3499684031513f0495e2780505f200efcbaba53eebfc7_1280.jpg",
    "science" to "https://pixabay.com/get/g465a023873108dc1aa21c318d1571a51d8d19af8266fec63385bd336a51de8b1beb999f1240f46c211d29f6dfc793794357bf52582edefafd656e2d8a39b13c8_1280.jpg",
    "education" to "https://pixabay.com/get/g775136ee3a140d9fb575cc4daff41a19c1031776f52ce893792be5147900b6f875d91ff6956ece8706682e35cf91827535c3e6ee59fb1d0bcf90bf53f6fab203_1280.jpg",
    "feelings" to "https://pixabay.com/get/g4a5d4aa2a4b1fbc5fd4bd56b87c1f38dfbf0d23b429f9decb63424f2515e6b1e73854205acb6cf7f21362c9fccb1c25358aa4a331de26e0e04443a8e83cf4cce_1280.jpg",
    "health" to "https://pixabay.com/get/gd9a352e23917c64d54559a744c5f78061eab2f03af2becceb1651ad69d4d741db25a6ea9bce19ba2a9d6a98e7e11db4006e7357c86fc685cdaac35f18f6e1a2e_1280.jpg",
    "people" to "https://pixabay.com/get/gf080457ed48d0d92b5b3954a0b1b198e872677b0881643115210b74eee4fb57e1ca1dadc06b4cb0a60ca35bc0e74cb86c0e242aa67c57701069c5609c5683807_1280.jpg",
    "places" to "https://pixabay.com/get/gce1bf13da370ee368e7018cb2d27d0059fed9bd375b4a2ce4852c3da20969f8e09022fa2063cc8a4219cf5bcef069c9fcbd49698481ae5a38ae164e10b7440ca_1280.jpg",
    "animals" to "https://pixabay.com/get/gc312d5b6e101d5bcbec1bb78b7b66420ed578cec0293a64e7ba86406ba0f5cc5418a78196f3d936e0a1d4af015b2e1b03e51eae1455da8bf7050918dedb63492_1280.jpg",
    "industry" to "https://pixabay.com/get/g65171dac1ff532d5f9fabfe758edcb3fa870993c3414857fa03e507234381e0a4871f78e8e8ede7f9a320756dbc3760b0d91b1e832a89b67ee5b3da0edaba8fb_1280.jpg",
    "computer" to "https://pixabay.com/get/g5ceff313ff4277b51234252e48e75eff5e56d2c0115ff8e36290b7b3a18da4d61b7b51e7af4c793ceee5c4233583dc737c165879f9ea1fdcfb9df4c509811b31_1280.jpg",
    "food" to "https://pixabay.com/get/ga5fd9cf86e30c323e6467792212705ec1e480d1cf55e61121586a18436ffe789fceb6fc67554b0a83bba511619868049a6ee663ca665d37d8645d9981fe43358_1280.jpg",
    "sports" to "https://pixabay.com/get/g2971752c08f3008f3d2400fbbd070f988676bb9912a82241cbb353cbd90b4a5703c7e5a6976bb62e409c90ff70009299fa35674a7dce6846123582b0fc3f991a_1280.jpg",
    "transportation" to "https://pixabay.com/get/g50f242b49562eeb82a90c7581e9c3cf40c0eeadfa94407ebeed23d680a64c947769a73c780351b22b80e6c84e6116e269f9db8772f766e587e7515f5f9cfe0a5_1280.jpg",
    "travel" to "https://pixabay.com/get/g8b1be52495df2bd68f3bdeed633d8e1a3e455988442b97efb6cdda8db05cb2f2099ec9ea83fabf274c574d5dd9decc2d9e817635512ea7db72cdd4c98c2102e0_1280.jpg",
    "buildings" to "https://pixabay.com/get/g858e75385bc4ab9d5fb49559c197f414d862701eb21385e3df726c5150b179c9d8cac6d36670828b46c93602e01bfca132ba426b99107584063af69ca54183ec_1280.jpg",
    "business" to "https://pixabay.com/get/ge059831e18519dd22c1703995dad6e0fba380830cd7844d6573334f495ec3ad4cca2304a98c770e861c419e210f24a8390e3e8797a39e7874ea49a4ab1474a56_1280.jpg"
  )
  val entities = ArrayList<CategoryEntity>()
  categoryData.forEach { pair ->
    entities.add(CategoryEntity(name = pair.first, coverImage = pair.second))
  }
  return entities
}


/*
INSERT INTO categories_table (name,coverImage) VALUES ("backgrounds" , "https://pixabay.com/get/g79fb12e7369d426c9dc790d85660e076ce01c7ce9a3d90fa5eab63a5f1f32b525a27e36608e4ce04020aafb2c96a10679a1cb4cd05859e8e4ddee9a9866ed058_1280.jpg")
INSERT INTO categories_table (name,coverImage) VALUES ("fashion" , "https://pixabay.com/get/g3760fa80880bcc2b3b84af502fa068e469975047443aeefc60138da1dbc4c384f175aa97f48a227d5ae9d74f807f3d104901dedf7c3f830099d037c3e8e2ccd0_1280.jpg")
INSERT INTO categories_table (name,coverImage) VALUES ("nature" ,"https://pixabay.com/get/gad96b74b7e0d2922fb3755239f4b006ffabde044c86cdf796638c388e966013079e5c5d2d984eaf6f0c3499684031513f0495e2780505f200efcbaba53eebfc7_1280.jpg" )
INSERT INTO categories_table (name,coverImage) VALUES ("science" , "https://pixabay.com/get/g465a023873108dc1aa21c318d1571a51d8d19af8266fec63385bd336a51de8b1beb999f1240f46c211d29f6dfc793794357bf52582edefafd656e2d8a39b13c8_1280.jpg" )
INSERT INTO categories_table (name,coverImage) VALUES ("education" , "https://pixabay.com/get/g775136ee3a140d9fb575cc4daff41a19c1031776f52ce893792be5147900b6f875d91ff6956ece8706682e35cf91827535c3e6ee59fb1d0bcf90bf53f6fab203_1280.jpg")
INSERT INTO categories_table (name,coverImage) VALUES ("feelings" , "https://pixabay.com/get/g4a5d4aa2a4b1fbc5fd4bd56b87c1f38dfbf0d23b429f9decb63424f2515e6b1e73854205acb6cf7f21362c9fccb1c25358aa4a331de26e0e04443a8e83cf4cce_1280.jpg" )
INSERT INTO categories_table (name,coverImage) VALUES ("health" , "https://pixabay.com/get/gd9a352e23917c64d54559a744c5f78061eab2f03af2becceb1651ad69d4d741db25a6ea9bce19ba2a9d6a98e7e11db4006e7357c86fc685cdaac35f18f6e1a2e_1280.jpg")
INSERT INTO categories_table (name,coverImage) VALUES ("people" , "https://pixabay.com/get/gf080457ed48d0d92b5b3954a0b1b198e872677b0881643115210b74eee4fb57e1ca1dadc06b4cb0a60ca35bc0e74cb86c0e242aa67c57701069c5609c5683807_1280.jpg" )
INSERT INTO categories_table (name,coverImage) VALUES ("places" , "https://pixabay.com/get/gce1bf13da370ee368e7018cb2d27d0059fed9bd375b4a2ce4852c3da20969f8e09022fa2063cc8a4219cf5bcef069c9fcbd49698481ae5a38ae164e10b7440ca_1280.jpg")
INSERT INTO categories_table (name,coverImage) VALUES ("animals" , "https://pixabay.com/get/gc312d5b6e101d5bcbec1bb78b7b66420ed578cec0293a64e7ba86406ba0f5cc5418a78196f3d936e0a1d4af015b2e1b03e51eae1455da8bf7050918dedb63492_1280.jpg" )
INSERT INTO categories_table (name,coverImage) VALUES ("industry" , "https://pixabay.com/get/g65171dac1ff532d5f9fabfe758edcb3fa870993c3414857fa03e507234381e0a4871f78e8e8ede7f9a320756dbc3760b0d91b1e832a89b67ee5b3da0edaba8fb_1280.jpg" )
INSERT INTO categories_table (name,coverImage) VALUES ("computer" , "https://pixabay.com/get/g5ceff313ff4277b51234252e48e75eff5e56d2c0115ff8e36290b7b3a18da4d61b7b51e7af4c793ceee5c4233583dc737c165879f9ea1fdcfb9df4c509811b31_1280.jpg" )
INSERT INTO categories_table (name,coverImage) VALUES ("food" , "https://pixabay.com/get/ga5fd9cf86e30c323e6467792212705ec1e480d1cf55e61121586a18436ffe789fceb6fc67554b0a83bba511619868049a6ee663ca665d37d8645d9981fe43358_1280.jpg")
INSERT INTO categories_table (name,coverImage) VALUES ("sports" , "https://pixabay.com/get/g2971752c08f3008f3d2400fbbd070f988676bb9912a82241cbb353cbd90b4a5703c7e5a6976bb62e409c90ff70009299fa35674a7dce6846123582b0fc3f991a_1280.jpg")
INSERT INTO categories_table (name,coverImage) VALUES ("transportation" , "https://pixabay.com/get/g50f242b49562eeb82a90c7581e9c3cf40c0eeadfa94407ebeed23d680a64c947769a73c780351b22b80e6c84e6116e269f9db8772f766e587e7515f5f9cfe0a5_1280.jpg")
INSERT INTO categories_table (name,coverImage) VALUES ("travel" , "https://pixabay.com/get/g8b1be52495df2bd68f3bdeed633d8e1a3e455988442b97efb6cdda8db05cb2f2099ec9ea83fabf274c574d5dd9decc2d9e817635512ea7db72cdd4c98c2102e0_1280.jpg" )
INSERT INTO categories_table (name,coverImage) VALUES ("buildings" , "https://pixabay.com/get/g858e75385bc4ab9d5fb49559c197f414d862701eb21385e3df726c5150b179c9d8cac6d36670828b46c93602e01bfca132ba426b99107584063af69ca54183ec_1280.jpg")
INSERT INTO categories_table (name,coverImage) VALUES ("business" , "https://pixabay.com/get/ge059831e18519dd22c1703995dad6e0fba380830cd7844d6573334f495ec3ad4cca2304a98c770e861c419e210f24a8390e3e8797a39e7874ea49a4ab1474a56_1280.jpg")
*/