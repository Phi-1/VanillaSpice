# Keep inventory on death so items can be cleared with a command
gamerule keepInventory true
scoreboard objectives add vs_death deathCount

# 20 tick loop
function vanillaspice:main_20t